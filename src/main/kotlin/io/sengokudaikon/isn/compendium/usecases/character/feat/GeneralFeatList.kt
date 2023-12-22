package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ListFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListFeatPort::class])
class GeneralFeatList(override val repository: FeatRepositoryPort) :
    GetList<FeatQuery.General, FeatModel>(), ListFeatPort {
    override fun getCacheKey(query: FeatQuery.General): String {
        query as FeatQuery.General.All
        return "model_feat:all:${query.page}:${query.size}"
    }
}
