package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import kotlinx.uuid.exposed.KotlinxUUIDTable

object BackgroundRules : KotlinxUUIDTable("background_rules") {
    val background = reference("background_id", Backgrounds)
    val rule = reference("rule", Rules)
}
