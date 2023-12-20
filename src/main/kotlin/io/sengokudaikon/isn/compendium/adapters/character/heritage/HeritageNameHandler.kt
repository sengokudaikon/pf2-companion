package io.sengokudaikon.isn.compendium.adapters.character.heritage

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameHeritagePort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HeritageNameHandler : ByNameHandler<HeritageModel, HeritageQuery.ByName, ByNameHeritagePort>() {
    override val useCase: ByNameHeritagePort by inject()
    override fun createQuery(name: String, id: String?): HeritageQuery.ByName {
        return HeritageQuery.ByName(name = name)
    }
}
