package io.sengokudaikon.isn.compendium.usecases.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.domain.vehicle.repository.VehicleRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdVehiclePort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdVehiclePort::class])
class VehicleById(override val repository: VehicleRepositoryPort) :
    GetById<VehicleQuery, VehicleModel>(), ByIdVehiclePort {
    override fun getCacheKey(query: VehicleQuery): String {
        query as VehicleQuery.ById
        return "model_vehicle:id:${query.id}"
    }
}