package io.sengokudaikon.isn.compendium.persistence.world.entity

import io.sengokudaikon.isn.compendium.domain.world.global.model.TraitType
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Traits : KotlinxUUIDTable("traits") {
    val name = varchar("name", 50)
    val description = text("description")
    val type = enumerationByName<TraitType>("type", 50).default(TraitType.GENERAL)
    val isHidden = bool("is_hidden").default(false)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("source", 255)
}
