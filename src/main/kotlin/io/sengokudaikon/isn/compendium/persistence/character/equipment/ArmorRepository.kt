package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ArmorRepositoryPort::class])
class ArmorRepository : ArmorRepositoryPort, BaseRepository<ArmorModel>() {
    override val collection: MongoCollection<ArmorModel> = getCollection("equipment")
    override val modelClass: KClass<ArmorModel> = ArmorModel::class

    override suspend fun findByName(name: String, criteria: Criteria): Result<ArmorModel> {
        return super.findByName(name, criteria.addCondition(eq("type", "armor")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<ArmorModel> {
        return super.findById(id, criteria.addCondition(eq("type", "armor")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<ArmorModel>> {
        return super.findAll(page, limit, criteria.addCondition(eq("type", "armor")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<ArmorModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "armor" }
        }
    }
}
