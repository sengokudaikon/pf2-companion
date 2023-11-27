package io.sengokudaikon.isn.compendium.domain.character.feat.entity

import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.persistence.world.entity.Feats
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.character.feat.model.Feat as ModelFeat

class Feat(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Feat>(Feats)

    var name by Feats.name
    var description by Feats.description
    var rarity by Feats.rarity
    var actions by Feats.actions
    var type by Feats.type
    var requirements by Feats.requirements
    var level by Feats.level
    var frequency by Feats.frequency
    var trigger by Feats.trigger
    var canSelectMultiple by Feats.canSelectMultiple
    var isDefault by Feats.isDefault
    var skillId by Feats.skillId
    var proficiencyId by Feats.proficiencyId
    var contentSrc by Feats.contentSrc

    fun toModel(): ModelFeat {
        return ModelFeat(
            this.name,
            this.description,
            this.rarity,
            this.actions,
            this.type,
            this.requirements,
            this.level,
            this.frequency,
            this.trigger,
            this.canSelectMultiple,
            this.isDefault,
            this.skillId ?: Skills.ACROBATICS,
            this.proficiencyId ?: io.sengokudaikon.isn.compendium.enums.Proficiency.UNTRAINED,
            this.contentSrc,
        )
    }
}
