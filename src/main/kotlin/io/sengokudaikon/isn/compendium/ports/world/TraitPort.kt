package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait
import io.sengokudaikon.isn.compendium.operations.world.trait.query.TraitQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface TraitPort : ReadPort<TraitQuery, Trait>
