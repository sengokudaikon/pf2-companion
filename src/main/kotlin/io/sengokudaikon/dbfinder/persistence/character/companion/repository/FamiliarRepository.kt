package io.sengokudaikon.dbfinder.persistence.character.companion.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.character.companion.entity.Familiar
import io.sengokudaikon.dbfinder.domain.character.companion.repository.FamiliarRepositoryPort
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.Familiars
import io.sengokudaikon.shared.persistence.repository.AbstractRepository
import kotlinx.uuid.UUID

class FamiliarRepository : AbstractRepository(), FamiliarRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Familiar> =
        query { Familiar.find { Familiars.name eq name }.first() }

    override suspend fun findById(id: UUID): Either<Throwable, Familiar> = query { Familiar.findById(id) }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Familiar>> = query { Familiar.all().limit(limit, (page - 1).toLong()).toList() }
}
