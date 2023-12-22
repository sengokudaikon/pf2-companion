package io.sengokudaikon.isn.compendium.operations.search.dto

enum class Comparison(val symbol: String) {
    EQUALS("eq"),
    NOT_EQUALS("neq"),
    GREATER_THAN("gt"),
    LESS_THAN("lt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
}