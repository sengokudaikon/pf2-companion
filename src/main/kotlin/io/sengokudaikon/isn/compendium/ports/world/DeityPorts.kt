package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListDeityPort : ReadPort<DeityQuery, List<DeityModel>>
interface ByIdDeityPort : ReadPort<DeityQuery, DeityModel>
interface ByNameDeityPort : ReadPort<DeityQuery, DeityModel>
