package io.sengokudaikon.isn.compendium.persistence.character.feat

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.GeneralFeatRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [GeneralFeatRepositoryPort::class])
class GeneralFeatRepository : BaseRepository<FeatModel>(), GeneralFeatRepositoryPort {
    override val collection: MongoCollection<FeatModel> = getCollection<FeatModel>("feats")
    override val modelClass: KClass<FeatModel> = FeatModel::class
    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<FeatModel>> {
        return super.findAll(page, limit, criteria.addCondition(eq("system.category", "general")))
    }
    override suspend fun findById(id: String, criteria: Criteria): Result<FeatModel> {
        return super.findById(id, criteria.addCondition(eq("system.category", "general")))
    }
    override suspend fun findByName(name: String, criteria: Criteria): Result<FeatModel> {
        return super.findByName(name,criteria.addCondition(eq("system.category", "general")))
    }
}
