package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class GeneralFeatIdHandler : ByIdHandler<FeatModel, FeatQuery.General.ById, ByIdFeatPort>() {
    override val useCase: ByIdFeatPort by inject()
    override fun createQuery(id: String): FeatQuery.General.ById {
        return FeatQuery.General.ById(id)
    }
}
