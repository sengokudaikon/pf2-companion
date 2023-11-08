package io.sengokudaikon.dbfinder.domain.character.background.entity

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AbilityBoost
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundBoosts
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BackgroundBoost(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    fun toModel(): AbilityBoost {
        return AbilityBoost(
            attribute = this.boostedAbility.name,
            value = 1,
        )
    }

    companion object : KotlinxUUIDEntityClass<BackgroundBoost>(BackgroundBoosts)

    var backgroundId by Background referencedOn BackgroundBoosts.backgroundId
    var boostedAbility by BackgroundBoosts.boostedAbility
}
