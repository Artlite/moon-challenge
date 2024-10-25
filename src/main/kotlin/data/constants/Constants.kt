package data.constants

import data.managers.SecurityManager
import io.ktor.util.*


/**
 * The `Constants` object holds static values used across the application.
 * It contains nested objects for organizing different types of constants.
 */
object Constants {

    /**
     * The `Configuraions` object holds configuration constants for the application.
     *
     * It provides:
     * - `BASE_URL`: The base URL for network requests or API endpoints.
     * - `CANDIDATE_ID`: A unique identifier for candidates used in the application.
     *   IMPORTANT: PUT CANDIDATE ID AS BASE64 VALUE (SIMPLE OBFUSCATION).
     */
    object Configuraions {
        val BASE_URL: String get() = SecurityManager().decode(
            "aHR0cHM6Ly9jaGFsbGVuZ2UuY3Jvc3NtaW50LmNvbS8="
        ).also { println("Base URL: $it") }
        val CANDIDATE_ID: String get() = SecurityManager().decode(
            "" //TODO: PUT CANDIDATE ID AS BASE64 VALUE HERE (SIMPLE OBFUSCATION).
        ).also { println("Candidate ID: $it") }
    }

}