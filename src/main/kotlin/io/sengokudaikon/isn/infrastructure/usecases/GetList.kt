package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class GetList<Q : Query, R : Model> :
    ReadPort<Q, List<R>>, CachingUseCase() {
    abstract val repository: RepositoryOutputPort<R>
    override suspend fun execute(query: Q): Result<List<R>> {
        query as Query.All<R>
        val filterQuery = query.filters
        val filters: List<Filter> = if (filterQuery.isNullOrEmpty()) {
            emptyList()
        } else {
            parseFilterExpression(filterQuery)
        }
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findAll(query.page, query.size, filters).getOrThrow()
            }
        }
    }

    abstract fun getCacheKey(query: Q): String
    private fun parseFilterExpression(expression: String): List<Filter> {
        val filters = mutableListOf<Filter>()

        // Remove the outer parentheses
        val innerExpression = expression.removeSurrounding("(", ")")

        // Split the expression into parts by the top-level commas
        val parts = splitByTopLevelCommas(innerExpression)

        for (part in parts) {
            if (part.startsWith("and(") || part.startsWith("or(")) {
                // If the part is another filter expression, parse it recursively
                filters.addAll(parseFilterExpression(part))
            } else {
                // If the part is an individual filter, parse it and add it to the list
                val filter = parseFilter(part)
                filters.add(filter)
            }
        }

        return filters
    }
    private fun parseFilter(filter: String): Filter {
        val matchResult = Regex("(\\w+)\\((\\w+),'(.*)'\\)").matchEntire(filter)
            ?: throw IllegalArgumentException("Invalid filter format: $filter")

        val comparison = matchResult.groupValues[1]
        val field = matchResult.groupValues[2]
        val value = matchResult.groupValues[3]

        return Filter(FilterType.valueOf(field), Comparison.valueOf(comparison.uppercase(Locale.getDefault())), value)
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
}
