package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.GeneralFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdFeatPort::class])
class GeneralFeatById(override val repository: GeneralFeatRepositoryPort) :
    GetById<FeatQuery.General, FeatModel>(),
    ByIdFeatPort {
    override fun getCacheKey(query: FeatQuery.General): String {
        query as FeatQuery.General.ById
        return "model_feat:id:${query.id}"
    }
}
