package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [EquipmentRepositoryPort::class])
class EquipmentRepository : BaseRepository<EquipmentModel>(), EquipmentRepositoryPort {
    override val modelClass: KClass<EquipmentModel> = EquipmentModel::class
    override val collection: MongoCollection<EquipmentModel> = getCollection("equipment")

    override suspend fun findByName(name: String, criteria: Criteria): Result<EquipmentModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "equipment")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<EquipmentModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "equipment")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<EquipmentModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "equipment")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<EquipmentModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "equipment" }
        }
    }
}
