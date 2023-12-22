package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import java.util.*

interface Filtering {
    fun parseFilterExpression(expression: String): List<Filter> {
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

        return Filter(
            FilterType.fromString(field),
            Comparison.valueOf(comparison.uppercase(Locale.getDefault())),
            value
        )
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
