package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.RuleChoices
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.RuleChoice as ModelRuleChoice

class RuleChoice(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<RuleChoice>(RuleChoices)

    var ruleId by Rule referencedOn RuleChoices.ruleId
    var name by RuleChoices.name
    var value by RuleChoices.value
    fun toModel(): ModelRuleChoice {
        return ModelRuleChoice(
            name = this.name,
            value = this.value,
        )
    }
}
