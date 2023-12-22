package io.sengokudaikon.isn.compendium.usecases.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.domain.vehicle.repository.VehicleRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ListVehiclePort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListVehiclePort::class])
class VehicleList(override val repository: VehicleRepositoryPort) :
    GetList<VehicleQuery, VehicleModel>(), ListVehiclePort {
    override fun getCacheKey(query: VehicleQuery): String {
        query as VehicleQuery.All
        return "model_vehicle:all:${query.page}:${query.size}"
    }
}