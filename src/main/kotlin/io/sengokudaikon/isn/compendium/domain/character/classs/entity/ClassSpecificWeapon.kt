package io.sengokudaikon.isn.compendium.domain.character.classs.entity

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Weapon
import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.ClassSpecificWeapons
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassSpecificWeapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassSpecificWeapon>(ClassSpecificWeapons)

    var classID by Class referencedOn ClassSpecificWeapons.classID
    var weapon by Weapon referencedOn ClassSpecificWeapons.weaponID
}