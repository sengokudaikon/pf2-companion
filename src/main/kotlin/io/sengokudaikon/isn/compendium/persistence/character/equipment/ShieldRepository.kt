package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ShieldRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ShieldRepositoryPort::class])
class ShieldRepository : BaseRepository<ShieldModel>(), ShieldRepositoryPort {
    override val collection: MongoCollection<ShieldModel> = getCollection("equipment")
    override val modelClass: KClass<ShieldModel> = ShieldModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<ShieldModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "shield")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<ShieldModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "shield")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<ShieldModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "shield")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<ShieldModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "shield" }
        }
    }
}
