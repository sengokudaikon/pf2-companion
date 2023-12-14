package io.sengokudaikon.isn.compendium.usecases.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.heritage.repository.HeritageRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdHeritagePort
import io.sengokudaikon.isn.infrastructure.usecases.GetById

class HeritageById(override val repository: HeritageRepositoryPort) :
    GetById<HeritageQuery, HeritageModel, HeritageRepositoryPort>(), ByIdHeritagePort {
    override fun getCacheKey(query: HeritageQuery): String {
        query as HeritageQuery.ById
        return "model_heritage:id:${query.id}"
    }
}
