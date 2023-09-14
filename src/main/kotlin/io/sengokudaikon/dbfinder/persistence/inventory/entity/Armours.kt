package io.sengokudaikon.dbfinder.persistence.inventory.entity

import io.sengokudaikon.dbfinder.persistence.enums.ArmourType
import io.sengokudaikon.dbfinder.persistence.enums.ProficiencyArmour
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Armours : KotlinxUUIDTable("armour") {
    val itemId = reference("item_id", io.sengokudaikon.dbfinder.persistence.world.entity.Items)
    val name = varchar("name", 50)
    val proficiency = enumerationByName<ProficiencyArmour>("proficiency", 10)
    val acBonus = integer("acBonus")
    val dexCap = integer("dexCap")
    val strengthRequired = integer("strength").default(0)
    val checkPenalty = integer("checkPenalty")
    val speedPenalty = integer("speedPenalty")
    val armourType = enumerationByName<ArmourType>("armourType", 10)
}
