package data.models

import data.constants.Constants
import kotlinx.serialization.Serializable

/**
 * CandidateModel is a concrete implementation of the CandidateableModel interface.
 *
 * It holds a unique identifier for a candidate, which is defined by the Constants.Configuraions.CANDIDATE_ID.
 * This model is used to represent and manage candidate-related data within the application.
 */
@Serializable
abstract class CandidateModel {
    val candidateId: String = Constants.Configuraions.CANDIDATE_ID
}