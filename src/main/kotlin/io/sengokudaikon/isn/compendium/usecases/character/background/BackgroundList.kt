package io.sengokudaikon.isn.compendium.usecases.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ListBackgroundPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListBackgroundPort::class])
class BackgroundList(override val repository: BackgroundRepositoryPort) :
    GetList<BackgroundQuery, BackgroundModel>(), ListBackgroundPort {
    override fun getCacheKey(query: BackgroundQuery): String {
        query as BackgroundQuery.All
        return "model_background:all:${query.page}:${query.size}"
    }
}
