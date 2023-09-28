package io.sengokudaikon.shared.operations

import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

interface Query<T> {
    @Serializable
    data class FindAll<T>(val page: Int, val size: Int) : Query<T>

    @Serializable
    data class FindById<T>(val id: UUID) : Query<T>

    @Serializable
    data class FindByName<T>(val name: String) : Query<T>
}
