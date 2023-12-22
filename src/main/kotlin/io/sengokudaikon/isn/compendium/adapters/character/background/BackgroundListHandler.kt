package io.sengokudaikon.isn.compendium.adapters.character.background

import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.ports.character.ListBackgroundPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackgroundListHandler : ListHandler<BackgroundModel, BackgroundQuery.All, ListBackgroundPort>() {
    override val useCase: ListBackgroundPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): BackgroundQuery.All {
        return BackgroundQuery.All(page, size).apply {
            this.filters = filters
            this.sort = sort
        }
    }
}
