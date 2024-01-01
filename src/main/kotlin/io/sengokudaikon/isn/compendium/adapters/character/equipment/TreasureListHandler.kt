package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.ports.character.ListTreasurePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class TreasureListHandler : ListHandler<TreasureModel, TreasureQuery.All, ListTreasurePort>() {
    override val useCase: ListTreasurePort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): TreasureQuery.All {
        return TreasureQuery.All(page, size).apply {
            this.filters = filters
        this.sort = sort
        }
    }
}
