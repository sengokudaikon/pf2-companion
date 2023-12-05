package io.sengokudaikon.isn.infrastructure.operations

import kotlinx.serialization.Serializable

interface Query<T> {
    @Serializable
    data class FindAll<T>(val page: Int, val size: Int) : Query<T>

    @Serializable
    data class FindById<T>(val id: String) : Query<T>

    @Serializable
    data class FindByName<T>(val name: String) : Query<T>
}
