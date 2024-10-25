package data.models.abs

import data.enums.ColorsEnum

/**
 * Interface representing a model that can be colored.
 *
 * This interface provides a property to access the color of an instance.
 */
interface ColoreableModel {
    val color: ColorsEnum
}