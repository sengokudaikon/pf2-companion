package io.sengokudaikon.dbfinder.domain.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanionAttributes
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanionSenses
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanionSkills
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanionWeapons
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanions
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.dbfinder.domain.character.companion.model.AnimalCompanion as ModelAnimalCompanion

class AnimalCompanion(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AnimalCompanion>(AnimalCompanions)

    var name by AnimalCompanions.name
    var rarity by AnimalCompanions.rarity
    var description by AnimalCompanions.description
    var size by AnimalCompanions.size
    var hp by AnimalCompanions.hp
    var ac by AnimalCompanions.ac
    var speed by AnimalCompanions.speed
    val senses by AnimalCompanionSense referrersOn AnimalCompanionSenses.animalCompanionID
    val attributes by AnimalCompanionAttribute referrersOn AnimalCompanionAttributes.animalCompanionID
    val weapons by AnimalCompanionWeapon referrersOn AnimalCompanionWeapons.animalCompanionID
    val skills by AnimalCompanionSkill referrersOn AnimalCompanionSkills.animalCompanionID

    suspend fun toModel(): ModelAnimalCompanion {
        return suspendedTransactionAsync {
            ModelAnimalCompanion(
                name = name,
                rarity = rarity.name,
                description = description,
                size = size.name,
                hp = hp,
                ac = ac,
                speed = speed,
                senses = senses.map { it.sense.name.name },
                attributes = attributes.map { it.value to it.attribute.name }.toMap(),
                weapons = weapons.map { it.weapon.name },
                skills = skills.map { it.proficiency.name to it.skill.name }.toMap(),
            )
        }.await()
    }
}
