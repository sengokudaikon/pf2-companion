package io.sengokudaikon.dbfinder.infrastructure.enums

enum class Size {
    TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
}

fun String.toSizeEnum(): Size {
    return when (this.lowercase()) {
        "tiny" -> Size.TINY
        "small" -> Size.SMALL
        "medium" -> Size.MEDIUM
        "large" -> Size.LARGE
        "huge" -> Size.HUGE
        "gargantuan" -> Size.GARGANTUAN
        else -> throw IllegalArgumentException("Cannot transform $this into Size enum")
    }
}
