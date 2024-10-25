package data.responses

import data.enums.GoalsEnum
import data.enums.toAscii
import kotlinx.serialization.Serializable

/**
 * A data class representing the structure of a goal response.
 *
 * This class is used with kotlinx.serialization to parse and serialize
 * the response which contains a grid of `GoalType` elements. The `goal`
 * property holds a list of lists, where each nested list represents a row
 * of `GoalType`.
 */
@Serializable
data class GoalResponse(
    val goal: List<List<GoalsEnum>>
): ApiResponse()

/**
 * Converts the `GoalResponse` object to a formatted ASCII representation.
 *
 * Each `GoalType` in the `goal` list is converted to its corresponding ASCII symbol,
 * and the symbols are arranged in a tab-separated and newline-separated format
 * to represent the grid structure of the goals.
 *
 * @return A string with the ASCII representation of the `goal` grid.
 */
fun GoalResponse.getStarMap(): String = goal
    .joinToString("\n") {
        it.joinToString("") {
            item -> item.toAscii()
        }
    }

/**
 * A model representing a filtered goal item with its type and coordinates.
 *
 * @param type The type of the goal (e.g., POLYANET, SOLOON, COMETH).
 * @param row The row index of the goal item.
 * @param column The column index of the goal item.
 */
data class GoalFilterModel(
    val type: GoalsEnum,
    val row: Int,
    val column: Int
)

/**
 * Filters the goals in the `GoalResponse` based on the specified types.
 *
 * @param types The types of goals to filter by. One or more `GoalsEnum` values.
 * @return A list of `GoalFilterModel` containing the filtered goals with their types and coordinates.
 */
fun GoalResponse.filterGoalsBy(types: List<GoalsEnum>): List<GoalFilterModel> {
    return goal.flatMapIndexed { rowIndex, rowList ->
        rowList.mapIndexedNotNull { columnIndex, element ->
            if (element in types) {
                GoalFilterModel(element, rowIndex, columnIndex)
            } else {
                null
            }
        }
    }
}