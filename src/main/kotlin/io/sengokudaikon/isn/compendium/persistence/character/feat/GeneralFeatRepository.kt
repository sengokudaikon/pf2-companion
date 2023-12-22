package io.sengokudaikon.isn.compendium.persistence.character.feat

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.GeneralFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [GeneralFeatRepositoryPort::class])
class GeneralFeatRepository : BaseRepository<FeatModel>(), GeneralFeatRepositoryPort {
    override val collection: MongoCollection<FeatModel> = getCollection<FeatModel>("feats")
    override val modelClass: KClass<FeatModel> = FeatModel::class
    override suspend fun findAll(
        page: Int,
        limit: Int,
        filters: List<Filter>,
        sort: List<Sort>
    ): Result<List<FeatModel>> {
        return super.findAll(page, limit, filters.plus(Filter(FilterType.Category, Comparison.EQ, "general")), sort)
    }
    override suspend fun findById(id: String, filters: List<Filter>): Result<FeatModel> {
        return super.findById(id, filters = filters.plus(Filter(FilterType.Category, Comparison.EQ, "general")))
    }
    override suspend fun findByName(name: String, filters: List<Filter>): Result<FeatModel> {
        return super.findByName(name, filters = filters.plus(Filter(FilterType.Category, Comparison.EQ, "general")))
    }
}
