package io.sengokudaikon.isn.compendium.ports.character

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface ListHeritagePort : ReadPort<HeritageQuery, List<HeritageModel>>
interface ByIdHeritagePort : ReadPort<HeritageQuery, HeritageModel>
interface ByNameHeritagePort : ReadPort<HeritageQuery, HeritageModel>
