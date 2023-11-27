package io.sengokudaikon.isn.compendium.domain.character.classs.entity

import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.ClassSpecificWeapons
import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.ClassWeapons
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassWeapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassWeapon>(ClassWeapons)

    var classID by Class referencedOn ClassWeapons.classID
    val weaponIDs by ClassSpecificWeapon referrersOn ClassSpecificWeapons.weaponID
    var weaponClass by ClassWeapons.weaponClass
    var proficiency by ClassWeapons.proficiency
}
