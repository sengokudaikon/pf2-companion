package io.sengokudaikon.isn.compendium.usecases.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameBackgroundPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameBackgroundPort::class])
class BackgroundByName(override val repository: BackgroundRepositoryPort) :
    GetByName<BackgroundQuery, BackgroundModel, BackgroundRepositoryPort>(), ByNameBackgroundPort {
    override fun getCacheKey(query: BackgroundQuery): String {
        query as BackgroundQuery.ByName
        return "model_background:name:${query.name}"
    }
}
