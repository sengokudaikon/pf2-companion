package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size
import kotlinx.uuid.exposed.KotlinxUUIDTable
object Ancestries : KotlinxUUIDTable("char_ancestries") {
    val img = varchar("img", length = 100)
    val name = varchar("name", length = 50)
    val rarity = enumerationByName<Rarity>(length = 10, name = "rarity").default(Rarity.COMMON)
    val hitPoints = integer("hit_points")
    val size = enumerationByName<Size>(length = 10, name = "size").default(Size.MEDIUM)
    val speed = integer("speed")
    val description = text("description")
    val visionSenseID = reference("vision_sense_id", VisionSenses)
    val additionalSenseID = reference("additional_sense_id", VisionSenses).nullable()
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
    val version = varchar("version", length = 10)
}
