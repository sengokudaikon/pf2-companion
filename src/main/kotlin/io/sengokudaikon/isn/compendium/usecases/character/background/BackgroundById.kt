package io.sengokudaikon.isn.compendium.usecases.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdBackgroundPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdBackgroundPort::class])
class BackgroundById(override val repository: BackgroundRepositoryPort) :
    GetById<BackgroundQuery, BackgroundModel, BackgroundRepositoryPort>(), ByIdBackgroundPort {
    override fun getCacheKey(query: BackgroundQuery): String {
        query as BackgroundQuery.ById
        return "model_background:id:${query.id}"
    }
}
