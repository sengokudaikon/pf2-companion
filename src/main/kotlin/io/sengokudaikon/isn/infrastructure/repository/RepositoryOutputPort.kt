package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.kotlin.client.coroutine.MongoCollection

interface RepositoryOutputPort<T : Any> {
    val collection: MongoCollection<T>
    suspend fun findByName(name: String): Result<T>
    suspend fun findById(id: String): Result<T>
    suspend fun findAll(page: Int, limit: Int): Result<List<T>>
    suspend fun findAllNames(): Result<List<String>>
    suspend fun findByNames(names: List<String>): Result<List<T>>
}
