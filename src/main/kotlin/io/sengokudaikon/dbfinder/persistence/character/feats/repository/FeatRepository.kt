package io.sengokudaikon.dbfinder.persistence.character.feats.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import io.sengokudaikon.dbfinder.domain.character.feat.repository.FeatRepositoryPort
import io.sengokudaikon.dbfinder.persistence.character.feats.entity.Feats
import io.sengokudaikon.shared.persistence.repository.AbstractRepository
import kotlinx.uuid.UUID
import org.koin.core.annotation.Single

@Single
class FeatRepository : AbstractRepository(), FeatRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Feat> {
        return query { Feat.find { Feats.name eq name }.first() }
    }

    override suspend fun findById(id: UUID): Either<Throwable, Feat> {
        return query { Feat.findById(id) }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Feat>> {
        return query { Feat.all().limit(limit, (page - 1).toLong()).toList() }
    }
}
