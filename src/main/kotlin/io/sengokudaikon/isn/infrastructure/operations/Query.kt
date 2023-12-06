package io.sengokudaikon.isn.infrastructure.operations

import kotlinx.serialization.Serializable

interface Query {
    @Serializable
    data class FindAll<T>(val page: Int, val size: Int) : Query

    @Serializable
    data class FindById<T>(val id: String) : Query

    @Serializable
    data class FindByName<T>(val name: String) : Query
}
