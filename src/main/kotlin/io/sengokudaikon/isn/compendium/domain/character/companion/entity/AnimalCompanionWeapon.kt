package io.sengokudaikon.isn.compendium.domain.character.companion.entity

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Weapon
import io.sengokudaikon.isn.compendium.persistence.character.companion.entity.AnimalCompanionWeapons
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnimalCompanionWeapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AnimalCompanionWeapon>(AnimalCompanionWeapons)

    var animalCompanion by AnimalCompanion referencedOn AnimalCompanionWeapons.animalCompanionID
    var weapon by Weapon referencedOn AnimalCompanionWeapons.weapon
}
