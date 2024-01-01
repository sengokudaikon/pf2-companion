package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.BackpackRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BackpackRepositoryPort::class])
class BackpackRepository: BaseRepository<BackpackModel>(), BackpackRepositoryPort {
    override val collection: MongoCollection<BackpackModel> = getCollection("equipment")
    override val modelClass: KClass<BackpackModel> = BackpackModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<BackpackModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "backpack")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<BackpackModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "backpack")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<BackpackModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "backpack")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<BackpackModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "backpack" }
        }
    }
}
