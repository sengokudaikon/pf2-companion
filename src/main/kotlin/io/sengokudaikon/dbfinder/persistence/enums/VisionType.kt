package io.sengokudaikon.dbfinder.persistence.enums

enum class VisionType {
    NORMAL,
    LOW_LIGHT,
    DARKVISION,
    TREMORSENSE,
    LIFESIGHT,
    BLINDSENSE,
    BLINDSIGHT,
}
fun String.toVisionEnum(): VisionType {
    return when (this.lowercase()) {
        "darkvision" -> VisionType.DARKVISION
        "lowlightvision" -> VisionType.LOW_LIGHT
        "tremorsense" -> VisionType.TREMORSENSE
        "lifesense" -> VisionType.LIFESIGHT
        "blindsense" -> VisionType.BLINDSENSE
        "blindsight" -> VisionType.BLINDSIGHT
        else -> VisionType.NORMAL
    }
}
