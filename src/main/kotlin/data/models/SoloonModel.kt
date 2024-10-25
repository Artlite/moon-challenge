package data.models

import data.enums.ColorsEnum
import data.models.abs.ColoreableModel
import data.models.abs.CoordinateableModel
import kotlinx.serialization.Serializable

/**
 * Data model representing a Soloon, which includes identifying and positioning details,
 * as well as its color.
 *
 * @property candidateId A unique identifier for the candidate associated with this Soloon.
 * @property row The row position for this Soloon within a coordinate system.
 * @property column The column position for this Soloon within a coordinate system.
 * @property color The color of this Soloon.
 */
@Serializable
data class SoloonModel(
    override val row: Int,
    override val column: Int,
    override val color: ColorsEnum
): CandidateModel(), CoordinateableModel, ColoreableModel
