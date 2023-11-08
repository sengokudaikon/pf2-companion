package io.sengokudaikon.dbfinder.persistence.character.classs.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassFeat
import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.fixtureloader.fixtures.model.ClassFeatureFixture
import kotlinx.uuid.UUID

class ClassFeatureRepository : ClassFeatureRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, ClassFeat> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: UUID): Either<Throwable, ClassFeat> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<ClassFeat>> {
        TODO("Not yet implemented")
    }

    override suspend fun findAllNames(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun batchInsert(models: Set<ClassFeatureFixture>) {
        TODO("Not yet implemented")
    }

    override suspend fun create(command: ClassFeatureFixture): Either<Throwable, ClassFeat> {
        TODO("Not yet implemented")
    }
}
