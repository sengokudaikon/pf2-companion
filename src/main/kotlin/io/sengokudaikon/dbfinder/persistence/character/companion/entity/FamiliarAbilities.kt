package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object FamiliarAbilities : KotlinxUUIDTable("char_familiar_abilities") {
    val name = varchar("name", 50)
    val description = varchar("description", 500)
    val familiar = reference("familiar", Familiars)
    val prerequisites = varchar("prerequisite", 50)
    val requirements = varchar("requirement", 50)
    val isMaster = bool("is_master").default(false)
    val code = varchar("code", 10)
    val contentSrc = varchar("content_src", 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
