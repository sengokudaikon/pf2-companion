package io.sengokudaikon.dbfinder.domain.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.FamiliarAbilities
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class FamiliarAbility(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<FamiliarAbility>(FamiliarAbilities)
    var name by FamiliarAbilities.name
    var description by FamiliarAbilities.description
    var prerequisites by FamiliarAbilities.prerequisites
    var requirements by FamiliarAbilities.requirements
    var homebrewID by FamiliarAbilities.homebrewID
    var familiar by Familiar referencedOn FamiliarAbilities.familiar
}
