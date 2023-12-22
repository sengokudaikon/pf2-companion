package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Filters.*
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import kotlin.reflect.KCallable
import kotlin.reflect.KClass

abstract class BaseRepository<T : Model> : RepositoryOutputPort<T>, Filtered {
    abstract override val collection: MongoCollection<T>
    abstract val modelClass: KClass<T>
    override suspend fun findById(id: String, filters: List<Filter>): Result<T> = runCatching {
        val type = getType(modelClass)
        val bsonFilters = withFilters(filters, type)
        bsonFilters.add((eq("_id", ObjectId(id))))
        val query = collection.find(and(bsonFilters)).firstOrNull()
        query ?: return Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
    }

    override suspend fun findByName(name: String, filters: List<Filter>): Result<T> = runCatching {
        val type = getType(modelClass)
        val bsonFilters = withFilters(filters, type)
        bsonFilters.add((eq("name", name)))
        val query = collection.find(and(bsonFilters)).firstOrNull()
        query ?: return Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
    }

    override suspend fun findAll(page: Int, limit: Int, filters: List<Filter>, sort: List<Sort>): Result<List<T>> = runCatching {
        val type = getType(modelClass)
        val bsonFilters = withFilters(filters, type)
        val sortDocument = withSorts(sort)
        val query = if (bsonFilters.isEmpty()) {
            collection.find()
        } else {
            collection.find(and(bsonFilters))
        }

        val result = query.sort(sortDocument).skip((page - 1) * limit).limit(limit).toList()
        result
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
