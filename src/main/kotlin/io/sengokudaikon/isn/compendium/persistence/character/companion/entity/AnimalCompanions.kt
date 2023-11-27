package io.sengokudaikon.isn.compendium.persistence.character.companion.entity

import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.VisionSenses
import io.sengokudaikon.isn.compendium.persistence.items.entity.Weapons
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanions : KotlinxUUIDTable("char_animal_companions") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 10)
    val description = text("description")
    val size = enumerationByName<io.sengokudaikon.isn.compendium.enums.Size>("size", length = 10)
    val hp = integer("hp")
    val ac = integer("ac")
    val speed = integer("speed")
}

object AnimalCompanionAttributes : KotlinxUUIDTable("animal_companion_attributes") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val attribute = enumerationByName<io.sengokudaikon.isn.compendium.enums.Ability>("attribute", 10)
    val value = integer("value")
}

object AnimalCompanionSenses : KotlinxUUIDTable("animal_companion_senses") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val sense = reference("sense", VisionSenses)
}

object AnimalCompanionSkills : KotlinxUUIDTable("animal_companion_skills") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val skill = enumerationByName<Skills>("skill", 50)
    val proficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency", 50).default(
            io.sengokudaikon.isn.compendium.enums.Proficiency.UNTRAINED,
        )
}

object AnimalCompanionWeapons : KotlinxUUIDTable("animal_companion_weapons") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val weapon = reference("weapon", Weapons)
}
