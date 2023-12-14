package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class GeneralFeatNameHandler(override val useCase: ByNameFeatPort) : ByNameHandler<FeatModel, FeatQuery.General.ByName, ByNameFeatPort>() {
    override fun createQuery(name: String, id: String?): FeatQuery.General.ByName {
        return FeatQuery.General.ByName(name)
    }
}
