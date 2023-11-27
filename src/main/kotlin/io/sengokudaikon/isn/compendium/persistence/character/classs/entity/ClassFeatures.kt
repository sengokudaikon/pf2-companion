package io.sengokudaikon.isn.compendium.persistence.character.classs.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassFeatures : KotlinxUUIDTable("char_class_features") {
    val classID = reference("class_id", Classes)
    val img = varchar("img", 255)
    val name = varchar("name", 255)
    val actionType = enumerationByName<io.sengokudaikon.isn.compendium.enums.ActionTypes>("action_type", 255)
    val actions = varchar("actions", 255)
    val category = varchar("category", 255)
    val description = text("description")
    val level = integer("level")
    val rules = jsonb("rules", GenericRule.serializer())
    val contentSrc = varchar("content_src", 255)
    val type = varchar("type", 255).default("feat")
    val canSelectMultiple = bool("can_select_multiple").default(false)
    val isDefault = bool("is_default").default(false)
}
