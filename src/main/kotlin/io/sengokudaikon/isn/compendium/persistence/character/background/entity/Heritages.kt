package io.sengokudaikon.isn.compendium.persistence.character.background.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Heritages : KotlinxUUIDTable("char_heritages") {
    val name = varchar("name", length = 50)
    val description = text("description")
    val ancestryID = reference("ancestry_id", Ancestries)
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 10)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val rules = jsonb("rules", GenericRule.serializer())
}
