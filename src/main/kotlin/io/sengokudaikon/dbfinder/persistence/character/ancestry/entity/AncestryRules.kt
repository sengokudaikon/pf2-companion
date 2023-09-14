package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryRules : KotlinxUUIDTable("char_ancestry_rules") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val ruleID = reference("rule_id", Rules)
}
