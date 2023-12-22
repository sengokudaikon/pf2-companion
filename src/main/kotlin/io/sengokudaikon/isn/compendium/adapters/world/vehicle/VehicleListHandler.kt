package io.sengokudaikon.isn.compendium.adapters.world.vehicle

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.compendium.ports.world.ListVehiclePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class VehicleListHandler :
    ListHandler<VehicleModel, VehicleQuery.All, ListVehiclePort>() {
    override val useCase: ListVehiclePort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): VehicleQuery.All {
        return VehicleQuery.All(page, size).apply {
            this.filters = filters
            this.sort = sort
        }
    }

}
