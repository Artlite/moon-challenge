package data.models

import data.enums.DirectionsEnum
import data.models.abs.CoordinateableModel
import data.models.abs.DirectionableModel
import kotlinx.serialization.Serializable

/**
 * Data class representing the model of a Cometh entity.
 *
 * This model combines various characteristics such as candidate identification,
 * coordinate-based positioning, and directional orientation.
 *
 * @property candidateId Unique identifier for the candidate.
 * @property row The row position of the Cometh within a coordinate system.
 * @property column The column position of the Cometh within a coordinate system.
 * @property direction The direction in which the Cometh is oriented, defined by `DirectionsEnum`.
 */
@Serializable
data class ComethModel(
    override val row: Int,
    override val column: Int,
    override val direction: DirectionsEnum
): CandidateModel(), CoordinateableModel, DirectionableModel
