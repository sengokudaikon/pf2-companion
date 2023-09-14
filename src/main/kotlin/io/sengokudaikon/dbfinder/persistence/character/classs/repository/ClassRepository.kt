package io.sengokudaikon.dbfinder.persistence.character.classs.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.character.classs.entity.Class
import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassRepositoryPort
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import io.sengokudaikon.shared.persistence.repository.AbstractRepository
import kotlinx.uuid.UUID
import org.koin.core.annotation.Single

@Single
class ClassRepository : AbstractRepository(), ClassRepositoryPort {
    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Class>> =
        query { Class.all().limit(limit, (page - 1).toLong()).toList() }

    override suspend fun findByName(name: String): Either<Throwable, Class> =
        query { Class.find { Classes.name eq name }.first() }

    override suspend fun findById(id: UUID): Either<Throwable, Class> = query { Class.findById(id) }
}
