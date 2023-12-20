package io.sengokudaikon.isn.compendium.enums

enum class ActionTypes {
    NONE,
    PASSIVE,
    FREE,
    REACTION,
    ACTION,
    TWO_ACTION,
    THREE_ACTION;

    companion object {
        fun fromString(value: String): ActionTypes {
            return when (value) {
                "NONE" -> NONE
                "PASSIVE" -> PASSIVE
                "FREE" -> FREE
                "REACTION" -> REACTION
                "ACTION" -> ACTION
                "TWO_ACTION" -> TWO_ACTION
                "THREE_ACTION" -> THREE_ACTION
                else -> NONE
            }
        }
    }
}
