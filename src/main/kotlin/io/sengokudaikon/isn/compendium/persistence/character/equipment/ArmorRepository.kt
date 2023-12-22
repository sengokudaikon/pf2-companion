package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ArmorRepositoryPort::class])
class ArmorRepository : ArmorRepositoryPort, BaseRepository<ArmorModel>() {
    override val collection: MongoCollection<ArmorModel> = getCollection("equipments")
    override val modelClass: KClass<ArmorModel> = ArmorModel::class

    override suspend fun findByName(name: String, filters: List<Filter>): Result<ArmorModel> {
        return super.findByName(name, filters.plus(Filter(FilterType.Type, Comparison.EQ, "armor")))
    }

    override suspend fun findById(id: String, filters: List<Filter>): Result<ArmorModel> {
        return super.findById(id, filters.plus(Filter(FilterType.Type, Comparison.EQ, "armor")))
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        filters: List<Filter>,
        sort: List<Sort>
    ): Result<List<ArmorModel>> {
        return super.findAll(page, limit, filters.plus(Filter(FilterType.Type, Comparison.EQ, "armor")), sort)
    }

    override suspend fun findByNames(names: List<String>): Result<List<ArmorModel>> {
        return super.findByNames(names).map {
            it.filter { it.type == "armor" }
        }
    }
}
