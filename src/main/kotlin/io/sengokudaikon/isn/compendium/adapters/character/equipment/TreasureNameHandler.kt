package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameTreasurePort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class TreasureNameHandler :
    ByNameHandler<TreasureModel, TreasureQuery.ByName, ByNameTreasurePort>() {
    override val useCase: ByNameTreasurePort by inject()
    override fun createQuery(name: String, id: String?): TreasureQuery.ByName {
        return TreasureQuery.ByName(name)
    }
}
