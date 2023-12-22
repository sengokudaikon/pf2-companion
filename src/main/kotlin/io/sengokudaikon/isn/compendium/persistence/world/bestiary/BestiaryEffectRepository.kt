package io.sengokudaikon.isn.compendium.persistence.world.bestiary

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryEffectModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryEffectRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BestiaryEffectRepositoryPort::class])
class BestiaryEffectRepository :
    BaseRepository<BestiaryEffectModel>(),
    BestiaryEffectRepositoryPort {
    override val modelClass: KClass<BestiaryEffectModel> = BestiaryEffectModel::class
    override val collection: MongoCollection<BestiaryEffectModel> =
        getCollection<BestiaryEffectModel>("bestiary-effects")
}
