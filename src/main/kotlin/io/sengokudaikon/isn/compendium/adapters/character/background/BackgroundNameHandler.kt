package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackgroundNameHandler :
    ByNameHandler<BackgroundModel, BackgroundQuery.ByName, ByNameBackgroundPort>() {
    override val useCase: ByNameBackgroundPort by inject()
    override fun createQuery(name: String, id: String?): BackgroundQuery.ByName {
        return BackgroundQuery.ByName(name)
    }
}
