package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackgroundIdHandler :
    ByIdHandler<BackgroundModel, BackgroundQuery.ById, ByIdBackgroundPort>() {
    override val useCase: ByIdBackgroundPort by inject()
    override fun createQuery(id: String): BackgroundQuery.ById {
        return BackgroundQuery.ById(id)
    }
}
