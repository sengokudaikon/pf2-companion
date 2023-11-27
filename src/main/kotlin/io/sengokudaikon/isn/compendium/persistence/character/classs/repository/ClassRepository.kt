package io.sengokudaikon.isn.compendium.persistence.character.classs.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.isn.builder.fixtures.model.ClassFixture
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.Class
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.ClassArmour
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.ClassSavingThrow
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.ClassSkill
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.ClassWeapon
import io.sengokudaikon.isn.compendium.domain.character.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.character.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.domain.world.item.entity.Weapon
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.enums.WeaponClass
import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.Classes
import io.sengokudaikon.isn.compendium.persistence.items.entity.Weapons
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
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
                keyAttribute =
                    command.system.keyAbility.value.map { io.sengokudaikon.isn.compendium.enums.Ability.valueOf(it) }
                        .first()
                rarity = io.sengokudaikon.isn.compendium.enums.Rarity.valueOf(command.system.traits.rarity)
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
                        proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.TRAINED
                    }
                },
            )
            clazz.savingThrows.plus(
                listOf(
                    command.system.savingThrows.fortitude.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = io.sengokudaikon.isn.compendium.enums.SavingThrows.Fortitude
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.savingThrows.reflex.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = io.sengokudaikon.isn.compendium.enums.SavingThrows.Reflex
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.savingThrows.will.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = io.sengokudaikon.isn.compendium.enums.SavingThrows.Will
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.perception.let { value ->
                        ClassSavingThrow.new {
                            classID = clazz
                            savingThrow = io.sengokudaikon.isn.compendium.enums.SavingThrows.Perception
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
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
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.simple.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.SIMPLE
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.martial.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.MARTIAL
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.advanced.let { value ->
                        ClassWeapon.new {
                            classID = clazz
                            weaponClass = WeaponClass.ADVANCED
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.attacks.other.let { value ->
                        val weapon = Weapon.find { Weapons.name eq value.name }.firstOrNull()?.id?.toString()
                            ?: Weapon.new { name = value.name }.id.toString()
                        ClassWeapon.new {
                            classID = clazz
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value.rank)
                        }.also { it.weaponIDs.plus(weapon) }
                    },
                ),
            )
            clazz.armours.plus(
                listOf(
                    command.system.defenses.light.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn.LIGHT
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.medium.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn.MEDIUM
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.heavy.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn.HEAVY
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
                        }
                    },
                    command.system.defenses.unarmored.let { value ->
                        ClassArmour.new {
                            classID = clazz
                            armour = io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn.NONE
                            proficiency = io.sengokudaikon.isn.compendium.enums.Proficiency.valueOf(value)
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
