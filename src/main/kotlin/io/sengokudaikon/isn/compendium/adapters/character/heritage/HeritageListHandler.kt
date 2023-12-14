package io.sengokudaikon.isn.compendium.adapters.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ListHeritagePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class HeritageListHandler(override val useCase: ListHeritagePort) : ListHandler<List<HeritageModel>, HeritageQuery.All, ListHeritagePort>() {
    override fun createQuery(page: Int, size: Int, id: String?): HeritageQuery.All {
        return HeritageQuery.All(page, size)
    }
}
