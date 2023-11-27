package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.AbilityFlaw
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryFlaws
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AncestryFlaw(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryFlaw>(AncestryFlaws)

    var ancestryID by Ancestry referencedOn AncestryFlaws.ancestryID
    var flaw by AncestryFlaws.flawedAbility

    fun toModel(): AbilityFlaw {
        return AbilityFlaw(
            this.flaw?.name,
            1,
        )
    }
}