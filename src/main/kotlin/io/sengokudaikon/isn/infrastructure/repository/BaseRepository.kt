package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.`in`
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlin.reflect.KCallable
import kotlin.reflect.KClass

abstract class BaseRepository<T : Model>(val kClass: KClass<T>) : RepositoryOutputPort<T> {
    abstract override val collection: MongoCollection<T>

    override suspend fun findById(id: String): Result<T> = find(kClass.members.first { it.name == "_id" }, id)
    override suspend fun findByName(name: String): Result<T> = find(kClass.members.first { it.name == "name" }, name)
    override suspend fun findAll(page: Int, limit: Int): Result<List<T>> = runCatching {
        collection.find().skip((page - 1) * limit).limit(limit).toList()
    }

    override suspend fun findAllNames(): Result<List<String>> = runCatching {
        collection.find().map { it.name }.toList()
    }

    override suspend fun findByNames(names: List<String>): Result<List<T>> {
        return runCatching {
            collection.find(`in`("name", names)).toList()
        }
    }

    suspend fun <R> find(field: KCallable<R>, value: R): Result<T> {
        return runCatching {
            collection.find(Filters.eq(field.name, value)).firstOrNull()
                ?: return Result.failure(DatabaseException.NotFound(kClass.qualifiedName))
        }
    }
}
