package io.sengokudaikon.dbfinder.persistence.inventory.entity

import io.sengokudaikon.dbfinder.persistence.enums.DamageDice
import io.sengokudaikon.dbfinder.persistence.enums.DamageType
import io.sengokudaikon.dbfinder.persistence.enums.WeaponClass
import io.sengokudaikon.dbfinder.persistence.enums.WeaponType
import io.sengokudaikon.dbfinder.persistence.world.entity.Items
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Weapons : KotlinxUUIDTable("weapons") {
    val itemId = reference("item_id", Items)
    val name = varchar("name", 50)
    val damageType = enumerationByName<DamageType>("damageType", 50)
    val damageDice = enumerationByName<DamageDice>("damage", 50)
    val weaponClass = enumerationByName<WeaponClass>("weaponClass", 50)
    val weaponType = enumerationByName<WeaponType>("weaponType", 50)
    val range = integer("range")
    val reload = integer("reload").nullable()
    val properties = varchar("properties", 50)
}
