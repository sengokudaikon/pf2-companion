package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.enums.Proficiency
import io.sengokudaikon.dbfinder.persistence.enums.SavingThrows
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassSavingThrows : KotlinxUUIDTable("class_saving_throws") {
    val classID = reference("class_id", Classes)
    val savingThrow = enumerationByName<SavingThrows>("saving_throw", 20)
    val proficiency = enumerationByName<Proficiency>("proficiency", 20)
}
