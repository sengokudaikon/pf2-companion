package io.sengokudaikon.isn.compendium.operations.world.vehicle.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface VehicleQuery : Query {
    @Resource("/api/vehicles")
    data class All(override val page: Int, override val size: Int,) :
        Query.All<List<VehicleModel>>, VehicleQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/vehicle/{id}")
    data class ById(override val id: String) : Query.ById<VehicleModel>, VehicleQuery

    @Resource("/api/vehicle")
    data class ByName(override val name: String) : Query.ByName<VehicleModel>, VehicleQuery
}
