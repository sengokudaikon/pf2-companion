package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.entity.Language
import io.sengokudaikon.dbfinder.domain.world.repository.LanguageRepositoryPort
import io.sengokudaikon.dbfinder.persistence.world.entity.Languages
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.world.model.Language as ModelLanguage

@Single(binds = [LanguageRepositoryPort::class])
class LanguageRepository : LanguageRepositoryPort {
    override suspend fun create(command: ModelLanguage): Either<Throwable, Language> {
        return suspendedTransactionAsync {
            Language.new {
                name = command.name
                description = command.description
            }
        }.await().right()
    }

    override suspend fun findByName(name: String): Either<Throwable, Language> {
        return suspendedTransactionAsync { Language.find { Languages.name eq name }.firstOrNull() }.await().let {
            it?.right() ?: Throwable("Language not found").left()
        }
    }

    override suspend fun findById(id: UUID): Either<Throwable, Language> {
        return suspendedTransactionAsync { Language.findById(id) }.await().let {
            it?.right() ?: Throwable("Language not found").left()
        }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Language>> {
        return suspendedTransactionAsync {
            Language.all().limit(limit, (page - 1).toLong()).toList()
        }.await().right()
    }

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync {
            Languages.slice(Languages.name).selectAll().map { it[Languages.name] }
        }.await()
    }

    override suspend fun batchInsert(models: Set<io.sengokudaikon.dbfinder.domain.world.model.Language>) {
        suspendedTransactionAsync {
            Languages.batchInsert(models) { language ->
                this[Languages.name] = language.name
                this[Languages.description] = language.description
            }
        }.await()
    }
}
