package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatEffectsRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single
class FeatEffectsRepository : BaseRepository<FeatEffectModel>(FeatEffectModel::class), FeatEffectsRepositoryPort {
    override val collection: MongoCollection<FeatEffectModel> = getCollection<FeatEffectModel>("featEffects")
}
