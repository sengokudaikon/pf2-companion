package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.enums.VisionType
import kotlinx.uuid.exposed.KotlinxUUIDTable

object VisionSenses : KotlinxUUIDTable("char_ancestry_senses") {
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
