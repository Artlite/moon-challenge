# Project: moon-challenge

## Steps to Run:

1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/);
2. Open the project and wait for synchronization to complete;
3. Open the file at `<project>/main/kotlin/data/constants/Constants.kt`;
4. At line 26, insert your **Candidate ID** as a [Base64](https://www.base64encode.org/) value;
```kotlin
val CANDIDATE_ID: String get() = SecurityManager().decode(
    "" //TODO: PUT CANDIDATE ID AS BASE64 VALUE HERE (SIMPLE OBFUSCATION).
).also { println("Candidate ID: $it") }
```
5. Run the project from `<project>/main/kotlin/core/Main.kt`.
```kotlin
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
```
