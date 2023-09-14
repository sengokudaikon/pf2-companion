package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object RuleChoices : KotlinxUUIDTable("rule_choices") {
    val ruleId = reference("rule_id", Rules)
    val name = varchar("name", 255)
    val value = varchar("value", 255)
}
