package io.sengokudaikon.isn.compendium.persistence.world.deity

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.domain.deity.repository.DeityRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [DeityRepositoryPort::class])
class DeityRepository : BaseRepository<DeityModel>(), DeityRepositoryPort {
    override val collection: MongoCollection<DeityModel> = getCollection("deities")
    override val modelClass: KClass<DeityModel> = DeityModel::class
}
