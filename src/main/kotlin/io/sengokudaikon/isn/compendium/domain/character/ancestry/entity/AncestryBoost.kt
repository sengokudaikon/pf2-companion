package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.AbilityBoost
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryBoosts
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AncestryBoost(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryBoost>(AncestryBoosts)

    var ancestryID by Ancestry referencedOn AncestryBoosts.ancestryID
    var boost by AncestryBoosts.boostedAbility

    fun toModel(): AbilityBoost {
        return AbilityBoost(
            this.boost.name,
            1,
        )
    }
}
