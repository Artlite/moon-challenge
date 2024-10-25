package data.models

import data.models.abs.CoordinateableModel
import kotlinx.serialization.Serializable

/**
 * PolyanetModel represents a model that can be associated with a candidate and can be
 * positioned within a coordinate system.
 *
 * @property candidateId The unique identifier for the candidate.
 * @property row The row coordinate of the model.
 * @property column The column coordinate of the model.
 */
@Serializable
data class PolyanetModel(
    override val row: Int,
    override val column: Int
) : CandidateModel(), CoordinateableModel
