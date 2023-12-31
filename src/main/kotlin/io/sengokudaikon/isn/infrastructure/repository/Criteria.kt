package io.sengokudaikon.isn.infrastructure.repository

import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Sorts
import io.sengokudaikon.isn.infrastructure.domain.Model
import org.bson.BsonDocument
import org.bson.conversions.Bson
import java.util.*
import kotlin.reflect.KClass

class Criteria {
    private val conditions = mutableListOf<Bson>()
    private var sort: Bson? = null
    var targetField: String? = null
    val searchParameters = mutableMapOf<String, String>()
    fun addCondition(condition: Bson): Criteria {
        conditions.add(condition)
        return this
    }

    fun addSort(sort: Bson): Criteria {
        this.sort = sort
        return this
    }

    fun build(): Bson {
        return if (conditions.isNotEmpty()) { and(conditions) } else BsonDocument()
    }

    fun getSort(): Bson {
        return sort ?: Sorts.ascending("id")
    }

    fun isEmpty(): Boolean {
        return conditions.isEmpty()
    }

    fun fromFilterString(filterString: String?): Criteria {
        if (filterString == null) {
            return this
        }
        val filters = parseFilterExpression(filterString)
        addCondition(filters)
        return this
    }

    fun fromSortString(sortString: String?): Criteria {
        if (sortString == null) {
            return this
        }
        val sorts = parseSortExpression(sortString)
        addSort(sorts)
        return this
    }

    @Suppress("ReturnCount")
    private fun parseFilterExpression(expression: String): Bson {
        if (expression.startsWith("and(")) {
            val innerExpression = expression.removePrefix("and(").removeSuffix(")")
            val parts = splitByTopLevelCommas(innerExpression)
            val bsonFilters = parts.map { parseFilterExpression(it) }
            return and(bsonFilters)
        } else if (expression.startsWith("or(")) {
            val innerExpression = expression.removePrefix("or(").removeSuffix(")")
            val parts = splitByTopLevelCommas(innerExpression)
            val bsonFilters = parts.map { parseFilterExpression(it) }
            return or(bsonFilters)
        } else {
            return parseFilter(expression)
        }
    }

    private fun splitByTopLevelCommas(expression: String): List<String> {
        val parts = mutableListOf<String>()
        var start = 0
        var parenthesesCount = 0

        for (i in expression.indices) {
            if (expression[i] == '(') {
                parenthesesCount++
            } else if (expression[i] == ')') {
                parenthesesCount--
            } else if (expression[i] == ',' && parenthesesCount == 0) {
                parts.add(expression.substring(start, i))
                start = i + 1
            }
        }

        parts.add(expression.substring(start))

        return parts
    }

    private fun parseFilter(part: String): Bson {
        val comparison = part.substringBefore("(")
        val contents = part.substringAfter("(").removeSuffix(")")
        val field = contents.substringBefore(",")
        val value = contents.substringAfter(",")

        return when (comparison) {
            "eq" -> eq(field, value)
            "neq" -> ne(field, value)
            "gt" -> gt(field, value)
            "lt" -> lt(field, value)
            "gte" -> gte(field, value)
            "lte" -> lte(field, value)
            "in" -> `in`(field, value)
            "nin" -> nin(field, value)
            "exists" -> exists(field, value.toBoolean())
            "regex" -> regex(field, value)
            "contains" -> elemMatch(field, regex("value", ".*$value*."))
            else -> throw IllegalArgumentException("Invalid comparison operator: $comparison")
        }
    }

    private fun parseSortExpression(sort: String): Bson {
        val sorts = mutableListOf<Bson>()
        if (sort.contains("and(")) {
            val innerExpression = sort.removeSurrounding("and(", ")")
            val parts = splitByTopLevelCommas(innerExpression)
            for (part in parts) {
                sorts.add(parseSort(part))
            }
        } else {
            val newSort = parseSort(sort)
            sorts.add(newSort)
        }
        return Sorts.orderBy(sorts)
    }

    private fun parseSort(sort: String): Bson {
        val matchResult = Regex("\\((\\w+),(\\w+)\\)").matchEntire(sort)
            ?: throw IllegalArgumentException("Invalid sort format: $sort")

        val field = matchResult.groupValues[1]
        val direction = matchResult.groupValues[2]

        return if (direction.lowercase(Locale.getDefault()) == "asc") {
            Sorts.ascending(field)
        } else {
            Sorts.descending(field)
        }
    }

    fun <T : Model> getType(model: KClass<T>): String {
        val type = model.simpleName?.lowercase() ?: ""
        return type.removeSuffix("model")
    }
}
