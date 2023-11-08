package io.sengokudaikon.dbfinder.persistence.items.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.DamageDice
import io.sengokudaikon.dbfinder.infrastructure.enums.DamageType
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponClass
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponType
import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Weapons : KotlinxUUIDTable("items_weapons") {
    val itemId = reference("item_id", Items)
    val name = varchar("name", 50)
    val damageType = enumerationByName<DamageType>("damage_type", 50)
    val damageDice = enumerationByName<DamageDice>("damage", 50)
    val weaponClass = enumerationByName<WeaponClass>("weapon_class", 50)
    val weaponType = enumerationByName<WeaponType>("weapon_type", 50)
    val range = integer("range")
    val reload = integer("reload").nullable()
}

object WeaponTraits : KotlinxUUIDTable("weapon_traits") {
    val weaponId = reference("weapon_id", Weapons)
    val traitId = reference("trait_id", Traits)
}
