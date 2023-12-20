package io.sengokudaikon.isn.compendium.adapters.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdVehiclePort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class VehicleIdHandler :
    ByIdHandler<VehicleModel, VehicleQuery.ById, ByIdVehiclePort>() {
    override val useCase: ByIdVehiclePort by inject()
    override fun createQuery(id: String, secondaryId: String?): VehicleQuery.ById {
        return VehicleQuery.ById(id)
    }
}
