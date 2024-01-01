package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.TreasureRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [TreasureRepositoryPort::class])
class TreasureRepository: BaseRepository<TreasureModel>(), TreasureRepositoryPort {
    override val collection: MongoCollection<TreasureModel> = getCollection("equipment")
    override val modelClass: KClass<TreasureModel> = TreasureModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<TreasureModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "treasure")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<TreasureModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "treasure")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<TreasureModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "treasure")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<TreasureModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "treasure" }
        }
    }
}
