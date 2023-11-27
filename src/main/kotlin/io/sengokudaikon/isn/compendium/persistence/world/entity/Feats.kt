package io.sengokudaikon.isn.compendium.persistence.world.entity

import io.sengokudaikon.isn.compendium.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Feats : KotlinxUUIDTable("feats") {
    val name = varchar("name", 50)
    val description = text("description")
    val actions = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionTypes>("action", length = 20)
    val type = enumerationByName<io.sengokudaikon.isn.compendium.enums.FeatTypes>("type", length = 20)
    val level = integer("level")
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 10)
    val requirements = text("requirements")
    val frequency = text("frequency")
    val trigger = text("trigger")
    val canSelectMultiple = bool("can_select_multiple")
    val isDefault = bool("is_default").default(false)
    val skillId = enumerationByName<Skills>("skill_id", length = 20).nullable()
    val proficiencyId =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency_id", length = 20).nullable()
    val contentSrc = varchar("content_src", length = 100)
    val isArchived = bool("is_archived").default(false)
}
