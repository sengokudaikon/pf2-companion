package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler

class BackgroundIdHandler(override val useCase: ByIdBackgroundPort) :
    ByIdHandler<BackgroundModel, BackgroundQuery.ById, ByIdBackgroundPort>() {
    override fun createQuery(id: String): BackgroundQuery.ById {
        return BackgroundQuery.ById(id)
    }
}
