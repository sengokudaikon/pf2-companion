package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.ports.character.ByIdTreasurePort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class TreasureIdHandler :
    ByIdHandler<TreasureModel, TreasureQuery.ById, ByIdTreasurePort>() {
    override val useCase: ByIdTreasurePort by inject()
    override fun createQuery(id: String): TreasureQuery.ById {
        return TreasureQuery.ById(id)
    }
}
