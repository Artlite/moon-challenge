package data.responses

import kotlinx.serialization.Serializable

/**
 * Represents a successful API response.
 * This class extends from ApiResponse, which serves as a base class for all API responses.
 */
@Serializable
class SuccessResponse : ApiResponse()