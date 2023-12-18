package io.sengokudaikon.isn.compendium.adapters.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ListHeritagePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HeritageListHandler : ListHandler<List<HeritageModel>, HeritageQuery.All, ListHeritagePort>() {
    override val useCase: ListHeritagePort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): HeritageQuery.All {
        return HeritageQuery.All(page, size)
    }
}
