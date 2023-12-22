package io.sengokudaikon.isn.compendium.persistence.character.feat

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.SkillFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [SkillFeatRepositoryPort::class])
class SkillFeatRepository: BaseRepository<FeatModel>(), SkillFeatRepositoryPort {
    override val collection: MongoCollection<FeatModel> = getCollection("feats")
    override val modelClass: KClass<FeatModel> = FeatModel::class
    override suspend fun findAll(
        page: Int,
        limit: Int,
        filters: List<Filter>,
        sort: List<Sort>
    ): Result<List<FeatModel>> {
        return super.findAll(page, limit, filters.plus(Filter(FilterType.Category, Comparison.EQ, "skill")), sort)
    }
    override suspend fun findById(id: String, filters: List<Filter>): Result<FeatModel> {
        return super.findById(id, filters = filters.plus(Filter(FilterType.Category, Comparison.EQ, "skill")))
    }
    override suspend fun findByName(name: String, filters: List<Filter>): Result<FeatModel> {
        return super.findByName(name, filters = filters.plus(Filter(FilterType.Category, Comparison.EQ, "skill")))
    }
}