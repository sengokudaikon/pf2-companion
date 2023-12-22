package io.sengokudaikon.isn.compendium.persistence.world.vehicle

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.domain.vehicle.repository.VehicleRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [VehicleRepositoryPort::class])
class VehicleRepository: BaseRepository<VehicleModel>(), VehicleRepositoryPort {
    override val collection: MongoCollection<VehicleModel> = getCollection("vehicles")
    override val modelClass: KClass<VehicleModel> = VehicleModel::class
}
