package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class GeneralFeatByName(override val repository: FeatRepositoryPort) :
    GetByName<FeatQuery.General, FeatModel, FeatRepositoryPort>(),
    ByNameFeatPort {
    override fun getCacheKey(query: FeatQuery.General): String {
        query as FeatQuery.General.ByName
        return "model_feat:name:${query.name}"
    }
}
