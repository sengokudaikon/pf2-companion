package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort

interface RepositoryOutputPort<T : Any> {
    val collection: MongoCollection<T>
    suspend fun findByName(name: String, filters: List<Filter> = listOf()): Result<T>
    suspend fun findById(id: String, filters: List<Filter> = listOf()): Result<T>
    suspend fun findAll(page: Int, limit: Int, filters: List<Filter> = listOf(), sort: List<Sort> = listOf()): Result<List<T>>
    suspend fun findByNames(names: List<String>): Result<List<T>>
}
