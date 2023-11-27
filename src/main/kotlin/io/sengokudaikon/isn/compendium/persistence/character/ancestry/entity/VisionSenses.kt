package io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity

import io.sengokudaikon.isn.compendium.enums.VisionType
import kotlinx.uuid.exposed.KotlinxUUIDTable

object VisionSenses : KotlinxUUIDTable("vision_senses") {
    val name = enumerationByName<VisionType>("name", length = 50).default(VisionType.NORMAL)
    val visionRange = integer("vision_range")
    val precision = enumerationByName<Precision>(
        "precision",
        length = 20,
    ).default(Precision.PRECISE)

    enum class Precision {
        IMPRECISE,
        PRECISE,
    }
}
