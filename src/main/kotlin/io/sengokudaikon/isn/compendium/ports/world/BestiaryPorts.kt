package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListBestiaryPort : ReadPort<BestiaryQuery, List<BestiaryModel>>
interface ByIdBestiaryPort : ReadPort<BestiaryQuery, BestiaryModel>
interface ByNameBestiaryPort : ReadPort<BestiaryQuery, BestiaryModel>
