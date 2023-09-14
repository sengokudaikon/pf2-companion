package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterWeapons
import io.sengokudaikon.dbfinder.domain.world.entity.Weapon
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterWeapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterWeapon>(CharacterWeapons)

    var character by Character referencedOn CharacterWeapons.characterID
    var weapon by Weapon referencedOn CharacterWeapons.weaponID
}
