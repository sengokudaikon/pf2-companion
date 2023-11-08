package io.sengokudaikon.dbfinder.domain.world.item.entity

import io.sengokudaikon.dbfinder.persistence.items.entity.SpellEffects
import io.sengokudaikon.dbfinder.persistence.items.entity.SpellTraits
import io.sengokudaikon.dbfinder.persistence.items.entity.Spells
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.item.model.Spell as ModelSpell

class Spell(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Spell>(Spells)

    var name by Spells.name
    var description by Spells.description
    var level by Spells.level
    var castTime by Spells.castTime
    val traits by SpellTrait referrersOn SpellTraits.spellID
    val effects by SpellEffect referrersOn SpellEffects.spellID
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
    var contentSrc by Spells.contentSrc

    fun toModel(): ModelSpell {
        return ModelSpell(
            name = this.name,
            description = this.description,
            level = this.level,
            castTime = this.castTime,
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
            contentSrc = this.contentSrc,
            traits = this.traits.map { it.toModel() },
            effects = this.effects.map { it.toModel() },
        )
    }
}
