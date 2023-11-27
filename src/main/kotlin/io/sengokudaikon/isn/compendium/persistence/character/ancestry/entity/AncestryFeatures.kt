package io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryFeatures : KotlinxUUIDTable("char_ancestry_features") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val name = varchar("name", 255)
    val image = varchar("image", 255)
    val description = text("description")
    val level = integer("level")
    val actionType = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionTypes>("action_type", 50)
    val actionNumber = integer("action_number").nullable().default(null)
    val category = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionCategories>("category", 50)
    val rules = jsonb("rules", GenericRule.serializer())
    val contentSrc = varchar("content_src", 255).default("")
    val trigger = text("trigger").default("")
}
