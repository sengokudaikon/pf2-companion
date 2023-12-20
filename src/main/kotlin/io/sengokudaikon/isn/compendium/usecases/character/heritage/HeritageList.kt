package io.sengokudaikon.isn.compendium.usecases.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.heritage.repository.HeritageRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ListHeritagePort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListHeritagePort::class])
class HeritageList(override val repository: HeritageRepositoryPort) :
    GetList<HeritageQuery, HeritageModel, List<HeritageModel>, HeritageRepositoryPort>(), ListHeritagePort {
    override fun getCacheKey(query: HeritageQuery): String {
        query as HeritageQuery.All
        return "model_heritage:all:${query.page}:${query.size}"
    }
}
