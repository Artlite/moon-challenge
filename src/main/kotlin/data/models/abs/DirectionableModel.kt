package data.models.abs

import data.enums.DirectionsEnum

/**
 * Represents a model that can be directed in one of the specified directions.
 *
 * This interface provides an abstraction for models that have a direction property
 * defined by the `DirectionsEnum`.
 */
interface DirectionableModel {
    val direction: DirectionsEnum
}