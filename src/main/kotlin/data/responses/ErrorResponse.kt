package data.responses

import kotlinx.serialization.Serializable

/**
 * Data class representing an error response in an API call.
 * Inherits from the ApiResponse base class to provide a standardized response format.
 *
 * @property error Indicates whether there was an error (true/false).
 * @property message A message describing the error.
 */
@Serializable
data class ErrorResponse(
    val error: Boolean,
    val message: String
) : ApiResponse()