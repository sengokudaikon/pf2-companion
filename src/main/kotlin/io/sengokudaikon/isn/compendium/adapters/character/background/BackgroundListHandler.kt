package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ListBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class BackgroundListHandler(override val useCase: ListBackgroundPort) :
    ListHandler<List<BackgroundModel>, BackgroundQuery.All, ListBackgroundPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): BackgroundQuery.All {
        return BackgroundQuery.All(page, size)
    }
}
