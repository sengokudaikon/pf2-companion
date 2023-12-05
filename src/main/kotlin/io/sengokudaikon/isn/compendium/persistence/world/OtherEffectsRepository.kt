package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.effects.model.OtherEffectsModel
import io.sengokudaikon.isn.compendium.domain.effects.repository.OtherEffectsRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class OtherEffectsRepository : BaseRepository<OtherEffectsModel>(OtherEffectsModel::class), OtherEffectsRepositoryPort {
    override val collection: MongoCollection<OtherEffectsModel> = getCollection("other-effects")
}
