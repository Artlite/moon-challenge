package data.responses

import kotlinx.serialization.Serializable

/**
 * Represents a base class for an API response.
 * This class is sealed, meaning it is designed to have a restricted hierarchy.
 */
@Serializable
sealed class ApiResponse