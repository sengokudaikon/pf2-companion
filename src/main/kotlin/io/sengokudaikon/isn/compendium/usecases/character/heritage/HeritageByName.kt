package io.sengokudaikon.isn.compendium.usecases.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.heritage.repository.HeritageRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameHeritagePort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class HeritageByName(override val repository: HeritageRepositoryPort) :
    GetByName<HeritageQuery, HeritageModel, HeritageRepositoryPort>(), ByNameHeritagePort {
    override fun getCacheKey(query: HeritageQuery): String {
        query as HeritageQuery.ByName
        return "model_heritage:name:${query.name}"
    }
}
