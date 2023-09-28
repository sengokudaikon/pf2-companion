package io.sengokudaikon.dbfinder.ports.world

import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.operations.world.trait.query.TraitQuery
import io.sengokudaikon.shared.ports.ReadPort

interface TraitPort : ReadPort<TraitQuery, Trait>
