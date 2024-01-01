package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.ports.character.ListKitPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class KitListHandler : ListHandler<KitModel, KitQuery.All, ListKitPort>() {
    override val useCase: ListKitPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): KitQuery.All {
        return KitQuery.All(page, size).apply {
            this.filters = filters
        this.sort = sort
        }
    }
}
