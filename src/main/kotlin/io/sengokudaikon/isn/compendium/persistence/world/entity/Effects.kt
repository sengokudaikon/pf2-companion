package io.sengokudaikon.isn.compendium.persistence.world.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Effects : KotlinxUUIDTable("effects") {
    val img = varchar("img", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val level = integer("level")
    val contentSrc = text("content_src")
    val rules = jsonb("rules", GenericRule.serializer())
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", 255)
}
