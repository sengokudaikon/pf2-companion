package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class BackgroundNameHandler(override val useCase: ByNameBackgroundPort) :
    ByNameHandler<BackgroundModel, BackgroundQuery.ByName, ByNameBackgroundPort>() {
    override fun createQuery(name: String, id: String?): BackgroundQuery.ByName {
        return BackgroundQuery.ByName(name)
    }
}
