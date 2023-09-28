package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import io.sengokudaikon.dbfinder.persistence.world.entity.Feats
import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import kotlinx.uuid.exposed.KotlinxUUIDTable

object BackgroundItems : KotlinxUUIDTable("background_items") {
    val background = reference("background_id", Backgrounds)
    val feat = reference("feat", Feats).nullable().default(null)
    val action = reference("action", Actions).nullable().default(null)
    val spell = reference("spell", Spells).nullable().default(null)
}
