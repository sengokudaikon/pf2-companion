package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.dbfinder.domain.world.entity.RuleChoice
import io.sengokudaikon.dbfinder.domain.world.repository.RuleRepositoryPort
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.world.model.Rule as ModelRule

@Single(binds = [RuleRepositoryPort::class])
class RuleRepository : RuleRepositoryPort {
    override suspend fun create(command: ModelRule): Either<Throwable, Rule> {
        return suspendedTransactionAsync {
            val rule = Rule.new {
                name = command.name
                description = command.description ?: ""
                mode = command.mode
                isArchived = command.isArchived
                contentSrc = command.contentSrc
                priority = command.priority
                prompt = command.prompt
            }
            command.ruleChoices.forEach { ruleChoice ->
                RuleChoice.new {
                    ruleId = rule
                    value = ruleChoice.value
                    name = ruleChoice.name
                }
            }

            rule
        }.await().right()
    }

    override suspend fun batchInsert(models: Set<ModelRule>) {
        suspendedTransactionAsync {
            Rules.batchInsert(models) { rule ->
                this[Rules.name] = rule.name
                this[Rules.description] = rule.description ?: ""
                this[Rules.mode] = rule.mode
                this[Rules.priority] = rule.priority
                this[Rules.prompt] = rule.prompt
            }
        }.await()
    }

    override suspend fun findByName(name: String): Either<Throwable, Rule> {
        return suspendedTransactionAsync { Rule.find { Rules.name eq name }.firstOrNull() }.await().let {
            it?.right() ?: Throwable("Rule not found").left()
        }
    }

    override suspend fun findById(id: UUID): Either<Throwable, Rule> {
        return suspendedTransactionAsync { Rule.findById(id) }.await().let {
            it?.right() ?: Throwable("Rule not found").left()
        }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Rule>> {
        return suspendedTransactionAsync {
            Rule.all().limit(limit, (page - 1).toLong()).toList()
        }.await().right()
    }

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync {
            Rules.slice(Rules.name).selectAll().map { it[Rules.name] }
        }.await()
    }
}
