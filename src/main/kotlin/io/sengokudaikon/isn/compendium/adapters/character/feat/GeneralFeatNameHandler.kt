package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class GeneralFeatNameHandler : ByNameHandler<FeatModel, FeatQuery.General.ByName, ByNameFeatPort>() {
    override val useCase: ByNameFeatPort by inject()
    override fun createQuery(name: String, id: String?): FeatQuery.General.ByName {
        return FeatQuery.General.ByName(name)
    }
}
