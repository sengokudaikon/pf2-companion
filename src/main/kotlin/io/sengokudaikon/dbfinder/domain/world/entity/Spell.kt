package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Spell as ModelSpell

class Spell(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Spell>(Spells)

    var name by Spells.name
    var description by Spells.description
    var level by Spells.level
    var school by Spells.school
    var castTime by Spells.castTime
    var components by Spells.components
    var range by Spells.range
    var cost by Spells.cost
    var trigger by Spells.trigger
    var requirements by Spells.requirements
    var targets by Spells.targets
    var duration by Spells.duration
    var saves by Spells.saves
    var rarity by Spells.rarity
    var damageDice by Spells.damageDice
    var damageType by Spells.damageType
    var heightenEvery by Spells.heightenEvery
    var heightenDice by Spells.heightenDice
    var heightenMultiplier by Spells.heightenMultiplier
    var isFocus by Spells.isFocus
    var isArchived by Spells.isArchived
    var contentSrc by Spells.contentSrc
    var homebrewID by Spells.homebrewID
    var version by Spells.version

    fun toModel(): ModelSpell {
        return ModelSpell(
            name = this.name,
            description = this.description,
            level = this.level,
            school = this.school,
            castTime = this.castTime,
            components = this.components,
            range = this.range,
            cost = this.cost,
            trigger = this.trigger,
            requirements = this.requirements,
            targets = this.targets,
            duration = this.duration,
            saves = this.saves,
            rarity = this.rarity,
            damageDice = this.damageDice,
            damageType = this.damageType,
            heightenEvery = this.heightenEvery,
            heightenDice = this.heightenDice,
            heightenMultiplier = this.heightenMultiplier,
            isFocus = this.isFocus,
            contentSrc = this.contentSrc,
            isArchived = this.isArchived,
            homebrewID = this.homebrewID,
        )
    }
}
