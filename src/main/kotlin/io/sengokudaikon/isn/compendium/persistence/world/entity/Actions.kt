package io.sengokudaikon.isn.compendium.persistence.world.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Actions : KotlinxUUIDTable("actions") {
    val name = varchar("name", 255)
    val image = varchar("image", 255)
    val description = text("description")
    val actionType = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionTypes>("action_type", 50)
    val actionNumber = integer("action_number").nullable().default(null)
    val category = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionCategories>("category", 50)
    val frequency = varchar("frequency", 255).default("")
    val requirements = text("requirements").default("")
    val rules = jsonb("rules", GenericRule.serializer())
    val contentSrc = varchar("content_src", 255).default("")
    val trigger = text("trigger").default("")
}