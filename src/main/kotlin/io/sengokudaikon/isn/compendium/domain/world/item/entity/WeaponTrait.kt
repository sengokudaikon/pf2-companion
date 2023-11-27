package io.sengokudaikon.isn.compendium.domain.world.item.entity

import io.sengokudaikon.isn.compendium.persistence.items.entity.WeaponTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WeaponTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<WeaponTrait>(WeaponTraits)

    var weaponId by WeaponTraits.weaponId
    var traitId by WeaponTraits.traitId
}