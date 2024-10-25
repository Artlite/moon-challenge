package data.requests

import data.enums.*
import data.models.ComethModel
import data.models.PolyanetModel
import data.models.SoloonModel
import data.responses.GoalResponse
import data.responses.filterGoalsBy

/**
 * Data class representing a request to apply a map operation involving different models.
 *
 * @property polyanets A list of `PolyanetModel` items representing polyanets in the map.
 * @property soloons A list of `SoloonModel` items representing soloons in the map.
 * @property comeths A list of `ComethModel` items representing comeths in the map.
 */
data class MapRequest(
    val polyanets: List<PolyanetModel>,
    val soloons: List<SoloonModel>,
    val comeths: List<ComethModel>
)

/**
 * Converts a `GoalResponse` into a `MapRequest` containing filtered and mapped models.
 *
 * This function filters the goals from the `GoalResponse` based on their types and maps them
 * to corresponding models (`PolyanetModel`, `SoloonModel`, `ComethModel`), organizing them into
 * a `MapRequest`.
 *
 * @return A `MapRequest` containing lists of `PolyanetModel`, `SoloonModel`, and `ComethModel`.
 */
fun GoalResponse.toMapRequest(): MapRequest = MapRequest(
    polyanets = filterGoalsBy(GoalsEnum.getPolyanets())
        .map { PolyanetModel(it.row, it.column) },
    soloons = filterGoalsBy(GoalsEnum.getSoloons())
        .map { SoloonModel(it.row, it.column, it.type.toColors()!!) },
    comeths = filterGoalsBy(GoalsEnum.getCommeths())
        .map { ComethModel(it.row, it.column, it.type.toDirections()!!) }
)