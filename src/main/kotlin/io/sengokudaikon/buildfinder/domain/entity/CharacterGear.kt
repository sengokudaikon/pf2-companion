package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterGears
import io.sengokudaikon.dbfinder.domain.world.entity.Item
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterGear(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterGear>(CharacterGears)
    var character by Character referencedOn CharacterGears.characterID
    var gear by Item referencedOn CharacterGears.gearID
    var isInvested by CharacterGears.isInvested
    var quantity by CharacterGears.quantity
}
