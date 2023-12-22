package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter

interface RepositoryOutputPort<T : Any> {
    val collection: MongoCollection<T>
    suspend fun findByName(name: String): Result<T>
    suspend fun findById(id: String): Result<T>
    suspend fun findAll(page: Int, limit: Int, filters: List<Filter>): Result<List<T>>
    suspend fun findByNames(names: List<String>): Result<List<T>>
}
