package io.sengokudaikon.isn.compendium.adapters.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameVehiclePort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class VehicleNameHandler :
    ByNameHandler<VehicleModel, VehicleQuery.ByName, ByNameVehiclePort>() {
    override val useCase: ByNameVehiclePort by inject()
    override fun createQuery(name: String, id: String?): VehicleQuery.ByName {
        return VehicleQuery.ByName(name)
    }
}
