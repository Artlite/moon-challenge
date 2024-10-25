package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum class representing different types of goal models.
 *
 * Each constant in this enum is annotated with @SerialName to specify the
 * serialized name that should be used when this enum is serialized or deserialized
 * with kotlinx.serialization.
 */
@Serializable
enum class GoalsEnum {
    @SerialName("SPACE") SPACE,
    @SerialName("POLYANET") POLYANET,
    @SerialName("WHITE_SOLOON") WHITE_SOLOON,
    @SerialName("BLUE_SOLOON") BLUE_SOLOON,
    @SerialName("PURPLE_SOLOON") PURPLE_SOLOON,
    @SerialName("RED_SOLOON") RED_SOLOON,
    @SerialName("RIGHT_COMETH") RIGHT_COMETH,
    @SerialName("LEFT_COMETH") LEFT_COMETH,
    @SerialName("UP_COMETH") UP_COMETH,
    @SerialName("DOWN_COMETH") DOWN_COMETH
}

/**
 * Returns the corresponding ASCII symbol for each GoalType.
 *
 * @return A string representing the ASCII symbol for the GoalType.
 */
fun GoalsEnum.toAscii(): String {
    return when (this) {
        GoalsEnum.SPACE -> "🌌"
        GoalsEnum.POLYANET -> "🪐"
        GoalsEnum.WHITE_SOLOON -> "🌕"
        GoalsEnum.BLUE_SOLOON -> "🌕"
        GoalsEnum.PURPLE_SOLOON -> "🌕"
        GoalsEnum.RED_SOLOON -> "🌕"
        GoalsEnum.RIGHT_COMETH -> "☄️"
        GoalsEnum.LEFT_COMETH -> "☄️"
        GoalsEnum.UP_COMETH -> "☄️"
        GoalsEnum.DOWN_COMETH -> "☄️"
    }
}

/**
 * Retrieves a list containing the POLYANET goal from the GoalsEnum.
 *
 * @return A list of GoalsEnum containing only the POLYANET goal.
 */
fun GoalsEnum.Companion.getPolyanets(): List<GoalsEnum> = listOf(
    GoalsEnum.POLYANET
)

/**
 * Retrieves a list of all soloon types from the GoalsEnum.
 *
 * @return a list containing WHITE_SOLOON, BLUE_SOLOON, PURPLE_SOLOON, and RED_SOLOON.
 */
fun GoalsEnum.Companion.getSoloons(): List<GoalsEnum> = listOf(
    GoalsEnum.WHITE_SOLOON,
    GoalsEnum.BLUE_SOLOON,
    GoalsEnum.PURPLE_SOLOON,
    GoalsEnum.RED_SOLOON
)

/**
 * Retrieves a list of `GoalsEnum` constants representing different directional comeths.
 *
 * @return A list containing `RIGHT_COMETH`, `LEFT_COMETH`, `UP_COMETH`, and `DOWN_COMETH`.
 */
fun GoalsEnum.Companion.getCommeths(): List<GoalsEnum> = listOf(
    GoalsEnum.RIGHT_COMETH,
    GoalsEnum.LEFT_COMETH,
    GoalsEnum.UP_COMETH,
    GoalsEnum.DOWN_COMETH
)