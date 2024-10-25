package core

import data.constants.Constants
import data.managers.MegaVerseManager
import data.requests.toMapRequest
import data.responses.getStarMap
import kotlinx.coroutines.runBlocking

/**
 * The entry point of the application.
 *
 * This function initializes an instance of `MegaVerseManager` using the
 * base URL and candidate ID provided by the `Constants.Configurations`.
 */
fun main() = runBlocking {
    val manager = MegaVerseManager(
        Constants.Configuraions.BASE_URL,
        Constants.Configuraions.CANDIDATE_ID
    )
    val goals = manager.getGoals() ?: return@runBlocking
    val starMap = goals.getStarMap()
    println(starMap)
    val mapRequest = goals.toMapRequest()
    manager.postMapRequest(
        it = mapRequest,
        stopWhenFailure = true,
        success = { request, success ->
            println("[SUCCESS] Request was succeeded: $request")
        },
        failure = { request, failure ->
            println("[FAILURE] Request was failed: $request")
        }
    )

}