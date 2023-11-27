package io.sengokudaikon.isn.compendium.persistence.items.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Armours : KotlinxUUIDTable("items_armour") {
    val itemId = reference("item_id", Items)
    val name = varchar("name", 50)
    val proficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn>("proficiency", 10)
    val acBonus = integer("ac_bonus")
    val dexCap = integer("dex_cap")
    val strengthRequired = integer("strength").default(0)
    val checkPenalty = integer("check_penalty").default(0)
    val speedPenalty = integer("speed_penalty").default(0)
    val armourType = enumerationByName<io.sengokudaikon.isn.compendium.enums.ArmourType>("armour_type", 10)
}

object ArmourTraits : KotlinxUUIDTable("item_armour_traits") {
    val armourId = reference("armour_id", Armours)
    val trait = varchar("trait", 50)
}
