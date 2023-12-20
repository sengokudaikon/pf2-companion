package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.`in`
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import kotlin.reflect.KCallable
import kotlin.reflect.KClass

abstract class BaseRepository<T : Model> : RepositoryOutputPort<T> {
    abstract override val collection: MongoCollection<T>
    abstract val modelClass: KClass<T>
    override suspend fun findById(id: String): Result<T> = runCatching {
        val result = collection.find(eq("_id", ObjectId(id))).firstOrNull()
        result ?: return Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
    }

    override suspend fun findByName(name: String): Result<T> = runCatching {
        val result = collection.find(eq("name", name)).firstOrNull()
        result ?: return Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
    }

    override suspend fun findAll(page: Int, limit: Int): Result<List<T>> = runCatching {
        collection.find().skip((page - 1) * limit).limit(limit).toList()
    }

    override suspend fun findByNames(names: List<String>): Result<List<T>> {
        return runCatching {
            val result = collection.find(`in`("name", names)).toList()
            result
        }
    }

    suspend fun <R> find(field: KCallable<R>, value: R): Result<T> {
        return runCatching {
            val result = collection.find(eq(field.name, value)).firstOrNull()
            result ?: return Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
        }
    }
}
