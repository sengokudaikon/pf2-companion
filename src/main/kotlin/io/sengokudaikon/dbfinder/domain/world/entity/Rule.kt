package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.RuleChoices
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.dbfinder.domain.world.model.Rule as ModelRule

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

    suspend fun toModel(): ModelRule {
        return suspendedTransactionAsync {
            ModelRule(
                name = name,
                description = description,
                mode = mode,
                isArchived = isArchived,
                contentSrc = contentSrc,
                priority = priority,
                prompt = prompt,
                ruleChoices = ruleChoices.map { it.toModel() },
            )
        }.await()
    }
}
