package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Field
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.`in`
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document
import org.bson.conversions.Bson
import kotlin.reflect.KClass

abstract class BaseRepository<T : Model> : RepositoryOutputPort<T> {
    abstract override val collection: MongoCollection<T>
    abstract val modelClass: KClass<T>
    data class AggregationParameter(
        val collectionName: String,
        val localField: String,
        val foreignField: String,
        val alias: String,
        val unwind: Boolean = false,
        val additionalFields: Map<String, Any>? = null
    )
    suspend fun aggregatedList(
        aggregationParameters: List<AggregationParameter>,
        criteria: Criteria,
        page: Int = 1,
        limit: Int = 25,
    ): Result<List<T>> {
        val result = runCatching {
            val aggregation = buildAggregation(aggregationParameters, criteria)
            aggregation.plus(Aggregates.skip((page - 1) * limit))
            aggregation.plus(Aggregates.limit(limit))
            collection.aggregate(aggregation).toList()
        }
        return listResult(result, modelClass)
    }

    suspend fun aggregatedFind(
        aggregationParameters: List<AggregationParameter>,
        criteria: Criteria,
    ): Result<T> {
        val result = runCatching {
            val aggregation = buildAggregation(aggregationParameters, criteria)
            collection.aggregate(aggregation).firstOrNull()
        }
        return singleResult(result, modelClass)
    }

    fun buildAggregation(
        aggregationParameters: List<AggregationParameter>,
        criteria: Criteria,
    ): List<Bson> {
        val pipeline = mutableListOf<Bson>()
        aggregationParameters.forEach { parameter ->
            parameter.additionalFields?.let { fields ->
                val addFieldsOperation = Aggregates.addFields(*fields.map { Field(it.key, it.value) }.toTypedArray())
                pipeline.add(addFieldsOperation)
            }
            val lookupOperation = Aggregates.lookup(
                parameter.collectionName,
                parameter.localField,
                parameter.foreignField,
                parameter.alias
            )
            pipeline.add(lookupOperation)
            if (parameter.unwind) {
                val unwindOperation = Aggregates.unwind("\$${parameter.alias}")
                pipeline.add(unwindOperation)
            }
        }
        val matchOperation = Aggregates.match(criteria.build())
        pipeline.add(0, matchOperation)
        criteria.targetField?.let {
            val projectionDocument = Document()
            aggregationParameters.forEach { parameter ->
                projectionDocument.append(it, Document("\$first", "${parameter.alias}.$it"))
            }
            val projectionOperation = Aggregates.project(projectionDocument)
            pipeline.add(projectionOperation)
        }

        return pipeline
    }
    fun <T : Model> singleResult(result: Result<T?>, modelClass: KClass<T>): Result<T> {
        return result.fold(
            onSuccess = { entity ->
                entity?.let { Result.success(it) }
                    ?: Result.failure(DatabaseException.NotFound(modelClass.qualifiedName))
            },
            onFailure = { exception ->
                Result.failure(DatabaseException.NotFound(modelClass.qualifiedName, previous = exception))
            }
        )
    }

    fun<T : Model> listResult(result: Result<List<T>>, modelClass: KClass<T>): Result<List<T>> {
        return result.fold(
            onSuccess = { entities ->
                entities.let { Result.success(it) }
            },
            onFailure = { exception ->
                Result.failure(DatabaseException.NotFound(modelClass.qualifiedName, previous = exception))
            }
        )
    }
    override suspend fun findById(id: String, criteria: Criteria): Result<T> {
        val result = runCatching {
            criteria.addCondition(eq("id", id))
            collection.find(criteria.build()).firstOrNull()
        }
        return singleResult(result, modelClass)
    }

    override suspend fun findByName(name: String, criteria: Criteria): Result<T> {
        val result = runCatching {
            criteria.addCondition(eq("name", name))
            collection.find(criteria.build()).firstOrNull()
        }
        return singleResult(result, modelClass)
    }

    override suspend fun findAll(page: Int, limit: Int, criteria: Criteria): Result<List<T>> {
        val result = runCatching {
            val query = if (criteria.isEmpty()) {
                collection.find()
            } else {
                collection.find(criteria.build())
            }

            val result = query.sort(criteria.getSort()).skip((page - 1) * limit).limit(limit).toList()
            result
        }
        return listResult(result, modelClass)
    }

    override suspend fun findByNames(names: List<String>): Result<List<T>> {
        return runCatching {
            val result = collection.find(`in`("name", names)).toList()
            result
        }
    }

    suspend fun find(criteria: Criteria): Result<T> {
        val result = runCatching {
            collection.find(criteria.build()).firstOrNull()
        }
        return singleResult(result, modelClass)
    }
}
