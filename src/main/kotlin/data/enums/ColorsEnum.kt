package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum class representing the possible colors of Soloons.
 *
 * Each constant is annotated with @SerialName to specify the serialized name.
 */
@Serializable
enum class ColorsEnum {
    @SerialName("blue") BLUE,
    @SerialName("red") RED,
    @SerialName("purple") PURPLE,
    @SerialName("white") WHITE
}

/**
 * Converts the current `GoalsEnum` to its corresponding `ColorsEnum`.
 *
 * @return The corresponding `ColorsEnum` value, or `null` if there is no direct mapping.
 */
fun GoalsEnum.toColors(): ColorsEnum? = when(this) {
    GoalsEnum.BLUE_SOLOON -> ColorsEnum.BLUE
    GoalsEnum.RED_SOLOON -> ColorsEnum.RED
    GoalsEnum.PURPLE_SOLOON -> ColorsEnum.PURPLE
    GoalsEnum.WHITE_SOLOON -> ColorsEnum.WHITE
    else -> null
}