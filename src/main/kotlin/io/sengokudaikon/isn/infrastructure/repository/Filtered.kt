package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.regex
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.domain.Model
import org.bson.Document
import org.bson.conversions.Bson
import kotlin.reflect.KClass

interface Filtered {
    fun withFilters(filters: List<Filter>, type: String?): MutableList<Bson> {
        val bsonFilters = mutableListOf<Bson>()
        filters.forEach {
            if ((!type.isNullOrEmpty() && type in it.key.applicableTypes) || it.key.applicableTypes.isEmpty() || type.isNullOrEmpty()) {
                val bsonFilter = when (it.comparison) {
                    Comparison.EQ -> Filters.eq(it.key.dbField, it.value)
                    Comparison.NEQ -> Filters.ne(it.key.dbField, it.value)
                    Comparison.GT -> Filters.gt(it.key.dbField, it.value)
                    Comparison.LT -> Filters.lt(it.key.dbField, it.value)
                    Comparison.GTE -> Filters.gte(it.key.dbField, it.value)
                    Comparison.LTE -> Filters.lte(it.key.dbField, it.value)
                    Comparison.IN -> Filters.`in`(it.key.dbField, it.value)
                    Comparison.NIN -> Filters.nin(it.key.dbField, it.value)
                    Comparison.EXISTS -> Filters.exists(it.key.dbField, it.value as Boolean)
                    Comparison.REGEX -> Filters.regex(it.key.dbField, it.value as String)
                    Comparison.CONTAINS -> Filters.elemMatch(
                        it.key.dbField,
                        regex("value", ".*${it.value}*.")
                    )
                }
                bsonFilters.add(bsonFilter)
            }
        }
        return bsonFilters
    }

    fun withSorts(sorts: List<Sort>): Bson {
        val sortDocument = Document()
        sorts.forEach { sort ->
            sortDocument.append(sort.field.dbField, if (sort.ascending) 1 else -1)
        }
        return sortDocument
    }

    fun <T: Model>getType(model: KClass<T>): String {
        val type = model.simpleName?.lowercase() ?: ""
        return type.removeSuffix("model")
    }
}