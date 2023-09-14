package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.RuleChoices
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Rule(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Rule>(Rules)

    var name by Rules.name
    var description by Rules.description
    var mode by Rules.mode
    var isArchived by Rules.isArchived
    var contentSrc by Rules.contentSrc
    var priority by Rules.priority
    var prompt by Rules.prompt
    val ruleChoices by RuleChoice referrersOn RuleChoices.ruleId
}
