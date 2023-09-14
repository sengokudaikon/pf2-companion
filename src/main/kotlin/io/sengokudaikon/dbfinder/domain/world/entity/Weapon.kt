package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.inventory.entity.Weapons
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Weapon(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Weapon>(Weapons)
    var name by Weapons.name
    var itemId by Weapons.itemId
    var damageType by Weapons.damageType
    var damageDice by Weapons.damageDice
    var weaponClass by Weapons.weaponClass
    var weaponType by Weapons.weaponType
    var range by Weapons.range
    var reload by Weapons.reload
}
