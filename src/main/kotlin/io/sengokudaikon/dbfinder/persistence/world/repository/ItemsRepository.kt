package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import io.sengokudaikon.dbfinder.domain.world.item.entity.Item
import io.sengokudaikon.dbfinder.domain.world.item.repository.ItemsRepositoryPort
import kotlinx.uuid.UUID
import io.sengokudaikon.dbfinder.domain.character.feat.model.Item as ModelItem

class ItemsRepository : ItemsRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Item> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: UUID): Either<Throwable, Item> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
    ): Either<Throwable, List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun findAllNames(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun batchInsert(models: Set<ModelItem>) {
        TODO("Not yet implemented")
    }

    override suspend fun create(command: ModelItem): Either<Throwable, Item> {
        TODO("Not yet implemented")
    }
}
