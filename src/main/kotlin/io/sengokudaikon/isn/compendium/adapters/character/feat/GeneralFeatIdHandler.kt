package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler

class GeneralFeatIdHandler(override val useCase: ByIdFeatPort) : ByIdHandler<FeatModel, FeatQuery.General.ById, ByIdFeatPort>() {
    override fun createQuery(id: String): FeatQuery.General.ById {
        return FeatQuery.General.ById(id)
    }
}
