package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import io.sengokudaikon.dbfinder.domain.character.feat.repository.FeatRepositoryPort
import io.sengokudaikon.dbfinder.persistence.world.entity.Feats
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.character.feat.model.Feat as ModelFeat

@Single
class FeatRepository : FeatRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Feat> {
        return suspendedTransactionAsync { Feat.find { Feats.name eq name }.first() }.await().right()
    }

    override suspend fun findById(id: UUID): Either<Throwable, Feat> {
        return suspendedTransactionAsync { Feat.findById(id) }.await()
            .let {
                it?.right() ?: DatabaseException.NotFound().left()
            }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Feat>> {
        return suspendedTransactionAsync { Feat.all().limit(limit, (page - 1).toLong()).toList() }.await().right()
    }

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync { Feats.slice(Feats.name).selectAll().map { it[Feats.name] } }.await()
    }

    override suspend fun batchInsert(models: Set<ModelFeat>) {
        suspendedTransactionAsync {
            Feats.batchInsert(models) { feat ->
                this[Feats.name] = feat.name
                this[Feats.description] = feat.description
                this[Feats.level] = feat.level
                this[Feats.type] = feat.type
                this[Feats.rarity] = feat.rarity
                this[Feats.trigger] = feat.trigger
                this[Feats.requirements] = feat.requirements
                this[Feats.contentSrc] = feat.contentSrc
                this[Feats.actions] = feat.actions
                this[Feats.isDefault] = feat.isDefault
                this[Feats.frequency] = feat.frequency
                this[Feats.proficiencyId] = feat.proficiencyId
                this[Feats.canSelectMultiple] = feat.canSelectMultiple
                this[Feats.version] = feat.version
            }
        }.await()
    }

    override suspend fun create(command: ModelFeat): Either<Throwable, Feat> {
        return suspendedTransactionAsync {
            val feat = Feat.new {
                name = command.name
                description = command.description
                level = command.level
                type = command.type
                rarity = command.rarity
                trigger = command.trigger
                requirements = command.requirements
                contentSrc = command.contentSrc
                actions = command.actions
                isDefault = command.isDefault
                frequency = command.frequency
                proficiencyId = command.proficiencyId
                canSelectMultiple = command.canSelectMultiple
                version = command.version
            }
            feat
        }.await().right()
    }
}
