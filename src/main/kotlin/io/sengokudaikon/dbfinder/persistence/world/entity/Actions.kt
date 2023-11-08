package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.ActionCategories
import io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes
import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import io.sengokudaikon.shared.persistence.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Actions : KotlinxUUIDTable("actions") {
    val name = varchar("name", 255)
    val image = varchar("image", 255)
    val description = text("description")
    val actionType = enumerationByName<ActionTypes>("action_type", 50)
    val actionNumber = integer("action_number").nullable().default(null)
    val category = enumerationByName<ActionCategories>("category", 50)
    val frequency = varchar("frequency", 255).default("")
    val requirements = text("requirements").default("")
    val rules = jsonb("rules", GenericRule.serializer())
    val contentSrc = varchar("content_src", 255).default("")
    val trigger = text("trigger").default("")
}
