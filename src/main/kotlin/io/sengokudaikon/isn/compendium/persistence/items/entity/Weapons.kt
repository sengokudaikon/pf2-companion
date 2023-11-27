package io.sengokudaikon.isn.compendium.persistence.items.entity

import io.sengokudaikon.isn.compendium.enums.WeaponClass
import io.sengokudaikon.isn.compendium.enums.WeaponType
import io.sengokudaikon.isn.compendium.persistence.world.entity.Traits
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Weapons : KotlinxUUIDTable("items_weapons") {
    val itemId = reference("item_id", Items)
    val name = varchar("name", 50)
    val damageType = enumerationByName<io.sengokudaikon.isn.compendium.enums.DamageType>("damage_type", 50)
    val damageDice = enumerationByName<io.sengokudaikon.isn.compendium.enums.DamageDice>("damage", 50)
    val weaponClass = enumerationByName<WeaponClass>("weapon_class", 50)
    val weaponType = enumerationByName<WeaponType>("weapon_type", 50)
    val range = integer("range")
    val reload = integer("reload").nullable()
}

object WeaponTraits : KotlinxUUIDTable("weapon_traits") {
    val weaponId = reference("weapon_id", Weapons)
    val traitId = reference("trait_id", Traits)
}
