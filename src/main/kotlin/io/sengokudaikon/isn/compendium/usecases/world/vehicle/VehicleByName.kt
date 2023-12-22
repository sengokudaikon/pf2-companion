package io.sengokudaikon.isn.compendium.usecases.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.domain.vehicle.repository.VehicleRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameVehiclePort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameVehiclePort::class])
class VehicleByName(override val repository: VehicleRepositoryPort) :
    GetByName<VehicleQuery, VehicleModel>(), ByNameVehiclePort {
    override fun getCacheKey(query: VehicleQuery): String {
        query as VehicleQuery.ByName
        return "model_vehicle:name:${query.name}"
    }
}