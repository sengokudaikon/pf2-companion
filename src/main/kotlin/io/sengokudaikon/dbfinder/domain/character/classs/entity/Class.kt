package io.sengokudaikon.dbfinder.domain.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassArmours
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSavingThrows
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSkills
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassWeapons
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Class(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Class>(Classes)
    var name by Classes.name
    var description by Classes.description
    var hitPoints by Classes.hitPoints
    var keyAttribute by Classes.keyAttribute
    var rarity by Classes.rarity
    var classDC by Classes.classDC
    var code by Classes.code
    val skillProficiencies by ClassSkill referrersOn ClassSkills.classID
    val savingThrows by ClassSavingThrow referrersOn ClassSavingThrows.classID
    val weapons by ClassWeapon referrersOn ClassWeapons.classID
    val armours by ClassArmour referrersOn ClassArmours.classID
    var homebrewID by Classes.homebrewID
    var version by Classes.version
}
