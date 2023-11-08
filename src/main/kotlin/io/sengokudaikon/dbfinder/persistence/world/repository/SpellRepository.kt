package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.world.item.entity.Spell
import io.sengokudaikon.dbfinder.domain.world.item.repository.SpellRepositoryPort
import kotlinx.uuid.UUID
import io.sengokudaikon.dbfinder.domain.world.item.model.Spell as ModelSpell

class SpellRepository : SpellRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Spell> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: UUID): Either<Throwable, Spell> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
    ): Either<Throwable, List<Spell>> {
        TODO("Not yet implemented")
    }

    override suspend fun findAllNames(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun batchInsert(models: Set<ModelSpell>) {
        TODO("Not yet implemented")
    }

    override suspend fun create(command: ModelSpell): Either<Throwable, Spell> {
        TODO("Not yet implemented")
    }
}
