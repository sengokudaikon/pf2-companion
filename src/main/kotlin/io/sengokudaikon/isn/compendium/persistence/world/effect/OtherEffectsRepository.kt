package io.sengokudaikon.isn.compendium.persistence.world.effect

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.effects.model.OtherEffectsModel
import io.sengokudaikon.isn.compendium.domain.effects.repository.OtherEffectsRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [OtherEffectsRepositoryPort::class])
class OtherEffectsRepository : BaseRepository<OtherEffectsModel>(), OtherEffectsRepositoryPort {
    override val modelClass: KClass<OtherEffectsModel> = OtherEffectsModel::class
    override val collection: MongoCollection<OtherEffectsModel> = getCollection("other-effects")
}
