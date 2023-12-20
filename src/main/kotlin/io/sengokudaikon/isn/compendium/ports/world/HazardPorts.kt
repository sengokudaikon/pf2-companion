package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListHazardPort : ReadPort<HazardQuery, List<HazardModel>>
interface ByIdHazardPort : ReadPort<HazardQuery, HazardModel>
interface ByNameHazardPort : ReadPort<HazardQuery, HazardModel>
