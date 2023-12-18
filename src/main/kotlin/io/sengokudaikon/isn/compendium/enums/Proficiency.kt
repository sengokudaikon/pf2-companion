package io.sengokudaikon.isn.compendium.enums

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
                2 -> TRAINED
                4 -> EXPERT
                6 -> MASTER
                8 -> LEGENDARY
                else -> throw IllegalArgumentException("Invalid value for Proficiency: $value")
            }
        }
    }
}
