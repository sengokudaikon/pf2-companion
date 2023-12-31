package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.kotlin.client.coroutine.MongoCollection

interface RepositoryOutputPort<T : Any> {
    val collection: MongoCollection<T>
    suspend fun findByName(name: String, criteria: Criteria): Result<T>
    suspend fun findById(id: String, criteria: Criteria): Result<T>
    suspend fun findAll(page: Int, limit: Int, criteria: Criteria): Result<List<T>>
    suspend fun findByNames(names: List<String>): Result<List<T>>
}
