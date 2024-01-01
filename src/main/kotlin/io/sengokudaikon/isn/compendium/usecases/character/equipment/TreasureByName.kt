package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.TreasureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameTreasurePort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameTreasurePort::class])
class TreasureByName(override val repository: TreasureRepositoryPort) :
    GetByName<TreasureQuery, TreasureModel>(), ByNameTreasurePort {
    override fun getCacheKey(query: TreasureQuery): String {
        query as TreasureQuery.ByName
        return "model_treasure:name:${query.name}"
    }
}
