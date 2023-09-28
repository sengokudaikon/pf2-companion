package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes
import io.sengokudaikon.dbfinder.infrastructure.enums.FeatTypes
import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Feats : KotlinxUUIDTable("feats") {
    val name = varchar("name", 50)
    val description = text("description")
    val actions = enumerationByName<ActionTypes>("action", length = 20)
    val type = enumerationByName<FeatTypes>("type", length = 20)
    val level = integer("level")
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val requirements = text("requirements")
    val frequency = text("frequency")
    val trigger = text("trigger")
    val canSelectMultiple = bool("can_select_multiple")
    val isDefault = bool("is_default").default(false)
    val skillId = enumerationByName<Skills>("skill_id", length = 20).nullable()
    val proficiencyId = enumerationByName<Proficiency>("proficiency_id", length = 20).nullable()
    val contentSrc = varchar("content_src", length = 100)
    val isArchived = bool("is_archived").default(false)
    val version = varchar("version", length = 10)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
