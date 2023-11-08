package io.sengokudaikon.dbfinder.domain.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassArmours
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassFeatures
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSavingThrows
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSkills
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassWeapons
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.dbfinder.domain.character.classs.model.Class as ModelClass

class Class(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Class>(Classes)

    var name by Classes.name
    var description by Classes.description
    var hitPoints by Classes.hitPoints
    var keyAttribute by Classes.keyAttribute
    var rarity by Classes.rarity
    var classDC by Classes.classDC
    var rules by Classes.rules
    val classFeats by ClassFeat referrersOn ClassFeatures.classID
    val skillProficiencies by ClassSkill referrersOn ClassSkills.classID
    val savingThrows by ClassSavingThrow referrersOn ClassSavingThrows.classID
    val weapons by ClassWeapon referrersOn ClassWeapons.classID
    val armours by ClassArmour referrersOn ClassArmours.classID
    val generalFeatLevels by Classes.generalFeatLevels
    val classFeatLevels by Classes.classFeatLevels
    val skillFeatLevels by Classes.skillFeatLevels
    val skillIncreaseLevels by Classes.skillIncreaseLevels
    var contentSrc by Classes.contentSrc

    suspend fun toModel(): ModelClass {
        return suspendedTransactionAsync {
            ModelClass(
                name = this@Class.name,
                description = this@Class.description,
                hitPoints = this@Class.hitPoints,
                keyAttribute = this@Class.keyAttribute.name,
                rarity = this@Class.rarity.name,
                classDC = classDC,
                proficiencies = skillProficiencies.map { it.proficiency.name to it.skillID.name }.toMap(),
                savingThrows = savingThrows.map { it.proficiency.name to it.savingThrow.name }.toMap(),
                weapons = weapons.map { it.proficiency.name to it.weaponClass.name }.toMap(),
                additionalWeapons = weapons.map { it.weaponIDs.map { it.weapon.name } }.flatten(),
                armours = armours.map { it.proficiency.name to it.armour.name }.toMap(),
            )
        }.await()
    }
}
