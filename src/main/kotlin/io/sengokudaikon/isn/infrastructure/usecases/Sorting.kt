package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.compendium.operations.search.dto.SortType
import java.util.*

interface Sorting {
    suspend fun parseSortExpression(sortQuery: String): List<Sort> {
        val sorts = mutableListOf<Sort>()
        if (sortQuery.contains("and(")) {
            val innerExpression = sortQuery.removeSurrounding("and(", ")")
            val parts = splitByTopLevelCommas(innerExpression)
            for (part in parts) {
                sorts.add(parseSort(part))
            }
        } else {
            val sort = parseSort(sortQuery)
            sorts.add(sort)
        }
        return sorts
    }

    private fun parseSort(sort: String): Sort {
        val matchResult = Regex("\\((\\w+),(\\w+)\\)").matchEntire(sort)
            ?: throw IllegalArgumentException("Invalid sort format: $sort")

        val field = matchResult.groupValues[1]
        val direction = matchResult.groupValues[2]

        return Sort(SortType.fromString(field), direction.lowercase(Locale.getDefault()) == "asc")
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