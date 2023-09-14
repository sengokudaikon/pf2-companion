package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

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
}
