package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum class representing the possible directions of Comeths.
 *
 * Each constant is annotated with @SerialName to specify the serialized name.
 */
@Serializable
enum class DirectionsEnum {
    @SerialName("up") UP,
    @SerialName("down") DOWN,
    @SerialName("left") LEFT,
    @SerialName("right") RIGHT
}

/**
 * Converts a `GoalsEnum` value to its corresponding `DirectionsEnum` value.
 *
 * This function maps specific `GoalsEnum` constants representing directions
 * to their corresponding `DirectionsEnum` constants.
 *
 * @return The corresponding `DirectionsEnum` value if the `GoalsEnum` value represents a direction,
 * or `null` if there is no corresponding direction.
 */
fun GoalsEnum.toDirections(): DirectionsEnum? = when(this) {
    GoalsEnum.UP_COMETH -> DirectionsEnum.UP
    GoalsEnum.DOWN_COMETH -> DirectionsEnum.DOWN
    GoalsEnum.LEFT_COMETH -> DirectionsEnum.LEFT
    GoalsEnum.RIGHT_COMETH -> DirectionsEnum.RIGHT
    else -> null
}