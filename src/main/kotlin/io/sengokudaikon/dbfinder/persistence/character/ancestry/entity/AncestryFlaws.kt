package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryFlaws : KotlinxUUIDTable("char_ancestry_flaws") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val flawedAbility = enumerationByName<Ability>(
        "flawed_ability",
        length = 20,
    ).nullable().default(Ability.None)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
