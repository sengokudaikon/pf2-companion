package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import io.sengokudaikon.shared.persistence.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Heritages : KotlinxUUIDTable("char_heritages") {
    val name = varchar("name", length = 50)
    val description = text("description")
    val ancestryID = reference("ancestry_id", Ancestries)
    val rarity = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Rarity>("rarity", length = 10)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val rules = jsonb("rules", GenericRule.serializer())
}
