package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ConsumableRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ConsumableRepositoryPort::class])
class ConsumableRepository: BaseRepository<ConsumableModel>(), ConsumableRepositoryPort {
    override val collection: MongoCollection<ConsumableModel> = getCollection("equipment")
    override val modelClass: KClass<ConsumableModel> = ConsumableModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<ConsumableModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "consumable")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<ConsumableModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "consumable")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<ConsumableModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "consumable")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<ConsumableModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "consumable" }
        }
    }
}
