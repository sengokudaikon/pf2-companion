package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterArmours
import io.sengokudaikon.dbfinder.domain.world.entity.Armour
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterArmour(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterArmour>(CharacterArmours)

    var character by Character referencedOn CharacterArmours.characterID
    var armour by Armour referencedOn CharacterArmours.armourID
}
