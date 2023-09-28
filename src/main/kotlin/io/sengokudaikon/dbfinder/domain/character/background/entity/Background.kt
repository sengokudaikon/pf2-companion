package io.sengokudaikon.dbfinder.domain.character.background.entity

import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundBoosts
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundItems
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundRules
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundSkills
import io.sengokudaikon.dbfinder.persistence.character.background.entity.Backgrounds
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.dbfinder.domain.character.background.model.Background as ModelBackground

class Background(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Background>(Backgrounds)

    var name by Backgrounds.name
    var rarity by Backgrounds.rarity
    var description by Backgrounds.description
    var code by Backgrounds.code
    var contentSrc by Backgrounds.contentSrc
    var trainedLore by Backgrounds.trainedLore
    val trainedSkills by BackgroundSkill referrersOn BackgroundSkills.background
    val boosts by BackgroundBoost referrersOn BackgroundBoosts.backgroundId
    val items by BackgroundItem referrersOn BackgroundItems.background
    val rules by BackgroundRule referrersOn BackgroundRules.background

    suspend fun toModel(): ModelBackground {
        return suspendedTransactionAsync {
            ModelBackground(
                id = this@Background.id.value.toString(),
                name = this@Background.name,
                rarity = this@Background.rarity,
                description = this@Background.description,
                code = this@Background.code,
                boosts = this@Background.boosts.map { it.toModel() },
                items = this@Background.items.map { it.toModel() },
                rules = this@Background.rules.map { it.toModel() },
                trainedLore = this@Background.trainedLore,
                trainedSkills = this@Background.trainedSkills.toList().map { it.skillId },
            )
        }.await()
    }
}
