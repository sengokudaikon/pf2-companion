package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryFlaws : KotlinxUUIDTable("char_ancestry_flaws") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val flawedAbility = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Ability>(
        "flawed_ability",
        length = 20,
    ).nullable().default(io.sengokudaikon.dbfinder.infrastructure.enums.Ability.None)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
