package io.sengokudaikon.isn.compendium.domain.world.item.entity

import io.sengokudaikon.isn.compendium.persistence.items.entity.WeaponTraits
import io.sengokudaikon.isn.compendium.persistence.items.entity.Weapons
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.item.model.Weapon as ModelWeapon

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
    val weaponTraits by WeaponTrait referrersOn WeaponTraits.weaponId
    fun toModel(): ModelWeapon {
        return ModelWeapon(
            name = this.name,
            itemId = this.itemId.toString(),
            damageType = this.damageType,
            damageDice = this.damageDice,
            weaponClass = this.weaponClass,
            weaponType = this.weaponType,
            range = this.range,
            reload = this.reload,
        )
    }
}