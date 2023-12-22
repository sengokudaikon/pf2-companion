package io.sengokudaikon.isn.compendium.persistence.world.hazard

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.domain.hazard.repository.HazardRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [HazardRepositoryPort::class])
class HazardRepository: BaseRepository<HazardModel>(), HazardRepositoryPort {
    override val collection: MongoCollection<HazardModel> = getCollection("hazards")
    override val modelClass: KClass<HazardModel> = HazardModel::class
}
