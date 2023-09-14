package io.sengokudaikon.dbfinder.domain.character.ancestry.entity

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AbilityFlaw
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryFlaws
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AncestryFlaw(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryFlaw>(AncestryFlaws)
    var ancestryID by Ancestry referencedOn AncestryFlaws.ancestryID
    var flaw by AncestryFlaws.flawedAbility
    var homebrewID by AncestryFlaws.homebrewID

    fun toModel(): AbilityFlaw {
        return AbilityFlaw(
            this.flaw?.name,
            1,
        )
    }
}
