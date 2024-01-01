package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.KitRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [KitRepositoryPort::class])
class KitRepository: BaseRepository<KitModel>(), KitRepositoryPort {
    override val collection: MongoCollection<KitModel> = getCollection("equipment")
    override val modelClass: KClass<KitModel> = KitModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<KitModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "kit")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<KitModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "kit")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<KitModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "kit")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<KitModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "kit" }
        }
    }
}
