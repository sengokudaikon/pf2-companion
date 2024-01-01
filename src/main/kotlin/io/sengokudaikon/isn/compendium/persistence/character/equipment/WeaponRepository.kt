package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [WeaponRepositoryPort::class])
class WeaponRepository : BaseRepository<WeaponModel>(), WeaponRepositoryPort {
    override val modelClass: KClass<WeaponModel> = WeaponModel::class
    override val collection: MongoCollection<WeaponModel> = getCollection("equipment")

    override suspend fun findByName(name: String, criteria: Criteria): Result<WeaponModel> {
        return super.findByName(name, criteria.addCondition(Filters.eq("type", "weapon")))
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<WeaponModel> {
        return super.findById(id, criteria.addCondition(Filters.eq("type", "weapon")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<WeaponModel>> {
        return super.findAll(page, limit, criteria.addCondition(Filters.eq("type", "weapon")))
    }

    override suspend fun findByNames(names: List<String>): Result<List<WeaponModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "weapon" }
        }
    }
}
