package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryEffectModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryEffectRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class BestiaryEffectRepository :
    BaseRepository<BestiaryEffectModel>(BestiaryEffectModel::class),
    BestiaryEffectRepositoryPort {

    override val collection: MongoCollection<BestiaryEffectModel> =
        getCollection<BestiaryEffectModel>("bestiary-effects")
}
