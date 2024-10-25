package data.managers

import data.models.ComethModel
import data.models.PolyanetModel
import data.models.SoloonModel
import data.requests.MapRequest
import data.responses.ErrorResponse
import data.responses.GoalResponse
import data.responses.SuccessResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.lang.Thread.sleep

/**
 * Interface representing a manager for interacting with the MegaVerse platform.
 */
interface MegaVerseManager {

    /**
     * Suspended function to retrieve the current goals from the MegaVerse platform.
     *
     * This function constructs a URL using the provided `url` and `candidateId`,
     * makes an HTTP GET request, and returns a `GoalResponse` object if successful.
     * If the request fails, it catches the exception and returns `null`.
     *
     * @return A `GoalResponse` containing the goal data, or `null` if the request fails.
     */
    suspend fun getGoals(): GoalResponse?


    /**
     * Suspended function to post a map request and handle the response.
     *
     * This function iterates through the lists of polyanets, soloons, and comeths
     * in the given `MapRequest` object, and processes each item using the `processRequest` method.
     *
     * @param it The MapRequest object containing polyanets, soloons, and comeths to be posted.
     * @param stopWhenFailure Indicates whether to stop processing further requests upon encountering a failure.
     * @param success A callback function to be invoked upon a successful request,
     *                accepting an `Any` type parameter along with an optional `SuccessResponse`.
     * @param failure A callback function to be invoked upon a failed request,
     *                accepting an `Any` type parameter along with an optional `ErrorResponse`.
     */
    suspend fun postMapRequest(
        it: MapRequest,
        stopWhenFailure: Boolean = true,
        success: (Any, SuccessResponse?) -> Unit,
        failure: (Any, ErrorResponse?) -> Unit
    )

    /**
     * Suspended function to delete a map request by iterating through various model items
     * in the given `MapRequest` object, and processing deletions using the `processRequest` method.
     *
     * @param it The `MapRequest` object containing polyanets, soloons, and comeths to be deleted.
     * @param stopWhenFailure Indicates whether to stop processing further deletions upon encountering a failure.
     * @param success A callback function to be invoked upon a successful deletion,
     *                accepting an `Any` type parameter along with an optional `SuccessResponse`.
     * @param failure A callback function to be invoked upon a failed deletion,
     *                accepting an `Any` type parameter along with an optional `ErrorResponse`.
     */
    suspend fun deleteMapRequest(
        it: MapRequest,
        stopWhenFailure: Boolean = true,
        success: (Any, SuccessResponse?) -> Unit,
        failure: (Any, ErrorResponse?) -> Unit
    )

    /**
     * Companion object.
     */
    companion object {

        /**
         * Creates an instance of MegaVerseManager for the specified candidate ID.
         *
         * @param candidateId The unique identifier for the candidate.
         * @return A new instance of MegaVerseManager associated with the given candidate ID.
         */
        operator fun invoke(
            url: String,
            candidateId: String
        ): MegaVerseManager = MegaVerseManagerImpl(url, candidateId)
    }

}

/**
 * Implementation of `MegaVerseManager` interface for managing interactions with the MegaVerse platform.
 *
 * @param url The base URL of the MegaVerse API.
 * @param candidateId The unique identifier for the candidate.
 */
private class MegaVerseManagerImpl(
    private val url: String,
    private val candidateId: String
) : MegaVerseManager {

    /**
     * Instance of the [HttpClient].
     */
    val client = HttpClient {
        install(ContentNegotiation) { json() }
        install(Logging) { level = LogLevel.NONE }
    }

    /**
     * Suspended function to retrieve the current goals from the MegaVerse platform.
     *
     * This function constructs a URL using the provided `url` and `candidateId`,
     * makes an HTTP GET request, and returns a `GoalResponse` object if successful.
     * If the request fails, it catches the exception and returns `null`.
     *
     * @return A `GoalResponse` containing the goal data, or `null` if the request fails.
     */
    override suspend fun getGoals(): GoalResponse? {
        return try {
            val url = String.format("%sapi/map/%s/goal", url, candidateId)
            client.get(url).body()
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    /**
     * Suspended function to post a map request and handle the response.
     *
     * This function iterates through the lists of polyanets, soloons, and comeths
     * in the given `MapRequest` object, and processes each item using the `processRequest` method.
     *
     * @param it The MapRequest object containing polyanets, soloons, and comeths to be posted.
     * @param stopWhenFailure Indicates whether to stop processing further requests upon encountering a failure.
     * @param success A callback function to be invoked upon a successful request,
     *                accepting an `Any` type parameter along with an optional `SuccessResponse`.
     * @param failure A callback function to be invoked upon a failed request,
     *                accepting an `Any` type parameter along with an optional `ErrorResponse`.
     */
    override suspend fun postMapRequest(
        it: MapRequest,
        stopWhenFailure: Boolean,
        success: (Any, SuccessResponse?) -> Unit,
        failure: (Any, ErrorResponse?) -> Unit
    ) {
        var shouldStop = false
        val failureCallback: (Any, ErrorResponse?) -> Unit = { model, result ->
            if (stopWhenFailure) {
                shouldStop = true
            }
            failure(model, result)
        }
        it.polyanets.forEach {
            if (shouldStop) return@postMapRequest
            processRequest(it, HttpMethod.Post, success, failureCallback)
        }
        it.soloons.forEach {
            if (shouldStop) return@postMapRequest
            processRequest(it, HttpMethod.Post, success, failureCallback)
        }
        it.comeths.forEach {
            if (shouldStop) return@postMapRequest
            processRequest(it, HttpMethod.Post, success, failureCallback)
        }
    }

    /**
     * Suspended function to delete a map request by iterating through various model items
     * in the given `MapRequest` object, and processing deletions using the `processRequest` method.
     *
     * @param it The `MapRequest` object containing polyanets, soloons, and comeths to be deleted.
     * @param stopWhenFailure Indicates whether to stop processing further deletions upon encountering a failure.
     * @param success A callback function to be invoked upon a successful deletion,
     *                accepting an `Any` type parameter along with an optional `SuccessResponse`.
     * @param failure A callback function to be invoked upon a failed deletion,
     *                accepting an `Any` type parameter along with an optional `ErrorResponse`.
     */
    override suspend fun deleteMapRequest(
        it: MapRequest,
        stopWhenFailure: Boolean,
        success: (Any, SuccessResponse?) -> Unit,
        failure: (Any, ErrorResponse?) -> Unit
    ) {
        var shouldStop = false
        val failureCallback: (Any, ErrorResponse?) -> Unit = { model, result ->
            if (stopWhenFailure) {
                shouldStop = true
            }
            failure(model, result)
        }
        it.polyanets.forEach {
            if (shouldStop) return@deleteMapRequest
            processRequest(it, HttpMethod.Delete, success, failureCallback)
        }
        it.soloons.forEach {
            if (shouldStop) return@deleteMapRequest
            processRequest(it, HttpMethod.Delete, success, failureCallback)
        }
        it.comeths.forEach {
            if (shouldStop) return@deleteMapRequest
            processRequest(it, HttpMethod.Delete, success, failureCallback)
        }
    }

    /**
     * Processes an HTTP request for various models and handles the response.
     *
     * @param it The model to be processed, which could be of type PolyanetModel, SoloonModel, or ComethModel.
     * @param method The HTTP method to use, either HttpMethod.Post or HttpMethod.Delete.
     * @param success A callback function to be invoked upon a successful request,
     *                accepting the same model and an optional SuccessResponse.
     * @param failure A callback function to be invoked upon a failed request,
     *                accepting the same model and an optional ErrorResponse.
     */
    suspend fun processRequest(
        it: Any,
        method: HttpMethod,
        success: (Any, SuccessResponse?) -> Unit,
        failure: (Any, ErrorResponse?) -> Unit,
        retryCount: Int = 0,
        currentDelay: Long = 1000L
    ): Unit = try {
        val url: String = when (it) {
            is PolyanetModel -> String.format("%sapi/polyanets", url)
            is SoloonModel -> String.format("%sapi/soloons", url)
            is ComethModel -> String.format("%sapi/comeths", url)
            else -> throw IllegalArgumentException("Unsupported REQUEST type")
        }

        val response: HttpResponse = when (method) {
            HttpMethod.Post -> {
                client.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(it)
                }
            }
            HttpMethod.Delete -> {
                client.delete(url) {
                    contentType(ContentType.Application.Json)
                    setBody(it)
                }
            }
            else -> throw IllegalArgumentException("Unsupported HTTP method")
        }

        if (response.status.isSuccess()) {
            success(it, SuccessResponse())
        } else if (response.status == HttpStatusCode.TooManyRequests) {
            val nextDelay: Long = (currentDelay * 2).coerceAtMost(8000L)
            println("Too many requests. Retrying after $nextDelay ms...")
            withContext(Dispatchers.IO) { kotlinx.coroutines.delay(nextDelay) }
            processRequest(it, method, success, failure, retryCount + 1, nextDelay)
        } else {
            val errorBody: String = response.body()
            val errorResponse: ErrorResponse = Json.decodeFromString(errorBody)
            failure(it, errorResponse)
        }
    } catch (e: ClientRequestException) {
        val errorBody: String = e.response.body()
        val errorResponse: ErrorResponse = Json.decodeFromString(errorBody)
        failure(it, errorResponse)
    } catch (e: ServerResponseException) {
        val errorBody: String = e.response.body()
        val errorResponse: ErrorResponse = Json.decodeFromString(errorBody)
        failure(it, errorResponse)
    } catch (e: Exception) {
        failure(it, ErrorResponse(error = true, message = e.message ?: "Unknown error"))
    }

}