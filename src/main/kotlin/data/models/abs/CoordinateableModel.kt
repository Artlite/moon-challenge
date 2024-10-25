package data.models.abs

/**
 * Interface representing a model that can be positioned within a coordinate system.
 *
 * @property row The row coordinate of the model.
 * @property column The column coordinate of the model.
 */
interface CoordinateableModel {
    val row: Int
    val column: Int
}