package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ListBackpackPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackpackListHandler : ListHandler<BackpackModel, BackpackQuery.All, ListBackpackPort>() {
    override val useCase: ListBackpackPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): BackpackQuery.All {
        return BackpackQuery.All(page, size).apply {
            this.filters = filters
        this.sort = sort
        }
    }
}
