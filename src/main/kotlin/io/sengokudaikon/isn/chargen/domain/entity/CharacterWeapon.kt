package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Weapon
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterWeapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterWeapon>(io.sengokudaikon.isn.chargen.persistence.entity.CharacterWeapons)

    var character by Character referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterWeapons.characterID
    var weapon by Weapon referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterWeapons.weaponID
}
