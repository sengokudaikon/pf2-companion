package io.sengokudaikon.dbfinder.persistence.character.classs.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.classs.entity.Class
import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassArmour
import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassSavingThrow
import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassSkill
import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassWeapon
import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.item.entity.Weapon
import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.ArmourProficiencyIn
import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.SavingThrows
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponClass
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import io.sengokudaikon.dbfinder.persistence.items.entity.Weapons
import io.sengokudaikon.fixtureloader.fixtures.model.ClassFixture
import io.sengokudaikon.shared.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single
class ClassRepository(
    private val featureRepository: ClassFeatureRepositoryPort,
) : ClassRepositoryPort {
    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Class>> =
        suspendedTransactionAsync { Class.all().limit(limit, (page - 1).toLong()).toList() }.await().right()

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync { Classes.slice(Classes.name).selectAll().map { it[Classes.name] } }.await()
    }

    override suspend fun batchInsert(models: Set<ClassFixture>) {
        TODO("Not yet implemented")
    }

    override suspend fun create(command: ClassFixture): Either<Throwable, Class> {
        return suspendedTransactionAsync {
            val clazz = Class.new {
                name = command.name
                description = command.system.description.value
                hitPoints = command.system.hp
                keyAttribute = command.system.keyAbility.value.map { Ability.valueOf(it) }.first()
                rarity = Rarity.valueOf(command.system.traits.rarity)
                classDC = command.system.classDC
                rules = command.system.rules.toString()
                contentSrc = command.system.publication.title
            }
            clazz.classFeats.plus(
                command.system.items.forEach { (_, value) ->
                    featureRepository.findByName(value.name).fold(
                        { throw it },
                        { it },
                    )
                },
            )
            clazz.skillProficiencies.plus(
                command.system.trainedSkills.value.forEach { value ->
                    ClassSkill.new {
                        classID = clazz
                        skillID = Skills.from(value)
                        proficiency = Proficiency.TRAINED
                    }
                },
            )
            clazz.savingThrows.plus(
                listOf(
                    command.system.savingThrows.fortitude.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = SavingThrows.Fortitude
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.savingThrows.reflex.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = SavingThrows.Reflex
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.savingThrows.will.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = SavingThrows.Will
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.perception.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = SavingThrows.Perception
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                ),
            )
            clazz.weapons.plus(
                listOf(
                    command.system.attacks.unarmed.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.UNARMED
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.simple.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.SIMPLE
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.martial.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.MARTIAL
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.advanced.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.ADVANCED
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.other.let { value ->
                        val weapon = Weapon.find { Weapons.name eq value.name }.firstOrNull()?.id?.toString()
                            ?: Weapon.new { name = value.name }.id.toString()
                        ClassWeapon.new {
                            classID = clazz
                            proficiency = Proficiency.valueOf(value.rank)
                        }.also { it.weaponIDs.plus(weapon) }
                    },
                ),
            )
            clazz.armours.plus(
                listOf(
                    command.system.defenses.light.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = ArmourProficiencyIn.LIGHT
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.medium.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = ArmourProficiencyIn.MEDIUM
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.heavy.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = ArmourProficiencyIn.HEAVY
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.unarmored.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = ArmourProficiencyIn.NONE
                            proficiency = Proficiency.valueOf(value)
                        }
                    },
                ),
            )
            clazz
        }.await().right()
    }

    override suspend fun findByName(name: String): Either<Throwable, Class> =
        suspendedTransactionAsync { Class.find { Classes.name eq name }.firstOrNull() }.await()?.right()
            ?: DatabaseException.NotFound("Class '$name' not found").left()

    override suspend fun findById(id: UUID): Either<Throwable, Class> =
        suspendedTransactionAsync { Class.findById(id) }.await()?.right()
            ?: DatabaseException.NotFound("Class '$id' not found").left()
}
