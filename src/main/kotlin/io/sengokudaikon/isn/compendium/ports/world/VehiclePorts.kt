package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.vehicle.VehicleModel
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListVehiclePort : ReadPort<VehicleQuery, List<VehicleModel>>
interface ByIdVehiclePort : ReadPort<VehicleQuery, VehicleModel>
interface ByNameVehiclePort : ReadPort<VehicleQuery, VehicleModel>
