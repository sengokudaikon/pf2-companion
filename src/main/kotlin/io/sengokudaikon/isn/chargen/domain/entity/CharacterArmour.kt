package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Armour
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterArmour(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterArmour>(io.sengokudaikon.isn.chargen.persistence.entity.CharacterArmours)

    var character by Character referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterArmours.characterID
    var armour by Armour referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterArmours.armourID
}
