package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassSavingThrows : KotlinxUUIDTable("class_saving_throws") {
    val classID = reference("class_id", Classes)
    val savingThrow = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.SavingThrows>("saving_throw", 20)
    val proficiency = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency>("proficiency", 20)
}
