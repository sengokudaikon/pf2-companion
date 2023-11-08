package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import io.sengokudaikon.shared.persistence.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Effects : KotlinxUUIDTable("effects") {
    val img = varchar("img", 255)
    val name = varchar("name", 255)
    val description = text("description")
    val level = integer("level")
    val contentSrc = text("content_src")
    val rules = jsonb("rules", GenericRule.serializer())
    val rarity = enumerationByName<Rarity>("rarity", 255)
}
