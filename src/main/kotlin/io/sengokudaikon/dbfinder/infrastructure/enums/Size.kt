package io.sengokudaikon.dbfinder.infrastructure.enums

enum class Size {
    TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
}

fun String.toSizeEnum(): Size {
    return when (this.lowercase()) {
        "tiny" -> Size.TINY
        "sm", "small" -> Size.SMALL
        "med", "medium" -> Size.MEDIUM
        "lg", "large" -> Size.LARGE
        "hg", "huge" -> Size.HUGE
        "grg", "gargantuan" -> Size.GARGANTUAN
        else -> throw IllegalArgumentException("Cannot transform $this into Size enum")
    }
}
