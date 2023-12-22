package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.GeneralFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameFeatPort::class])
class GeneralFeatByName(override val repository: GeneralFeatRepositoryPort) :
    GetByName<FeatQuery.General, FeatModel>(),
    ByNameFeatPort {
    override fun getCacheKey(query: FeatQuery.General): String {
        query as FeatQuery.General.ByName
        return "model_feat:name:${query.name}"
    }
}
