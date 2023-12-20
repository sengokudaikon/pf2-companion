package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatEffectsRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [FeatEffectsRepositoryPort::class])
class FeatEffectsRepository : BaseRepository<FeatEffectModel>(), FeatEffectsRepositoryPort {
    override val modelClass: KClass<FeatEffectModel> = FeatEffectModel::class
    override val collection: MongoCollection<FeatEffectModel> = getCollection<FeatEffectModel>("featEffects")
}
