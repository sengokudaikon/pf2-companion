package io.sengokudaikon.isn.compendium.operations.search.dto

import kotlinx.serialization.Serializable

@Serializable
sealed class SortType(open val dbField: String) {
    data object Type : SortType("type")
    data object Level : SortType("system.level")

    companion object {
        fun fromString(value: String): SortType {
            return when (value) {
                "type" -> Type
                "level" -> Level
                else -> throw IllegalArgumentException("Invalid sort type: $value")
            }
        }
    }
}