package io.sengokudaikon.isn.compendium.adapters.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdHeritagePort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HeritageIdHandler : ByIdHandler<HeritageModel, HeritageQuery.ById, ByIdHeritagePort>() {
    override val useCase: ByIdHeritagePort by inject()
    override fun createQuery(id: String, secondaryId: String?): HeritageQuery.ById {
        return HeritageQuery.ById(id)
    }
}
