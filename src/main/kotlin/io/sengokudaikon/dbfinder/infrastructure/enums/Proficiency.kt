package io.sengokudaikon.dbfinder.infrastructure.enums

enum class Proficiency {
    UNTRAINED,
    TRAINED,
    EXPERT,
    MASTER,
    LEGENDARY,
    ;

    companion object {
        fun valueOf(value: Int): Proficiency {
            return when (value) {
                0 -> UNTRAINED
                1 -> TRAINED
                2 -> EXPERT
                3 -> MASTER
                4 -> LEGENDARY
                else -> throw IllegalArgumentException("Invalid value for Proficiency: $value")
            }
        }
    }
}
