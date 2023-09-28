package io.sengokudaikon.dbfinder.infrastructure.enums

enum class Size {
    TINY, SMALL, MEDIUM, LARGE, HUGE, GARGANTUAN
}

fun String.toSizeEnum(): io.sengokudaikon.dbfinder.infrastructure.enums.Size {
    return when (this.lowercase()) {
        "tiny" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.TINY
        "small" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.SMALL
        "medium" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.MEDIUM
        "large" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.LARGE
        "huge" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.HUGE
        "gargantuan" -> io.sengokudaikon.dbfinder.infrastructure.enums.Size.GARGANTUAN
        else -> throw IllegalArgumentException("Cannot transform $this into Size enum")
    }
}
