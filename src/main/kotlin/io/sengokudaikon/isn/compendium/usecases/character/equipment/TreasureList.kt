package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.TreasureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.ports.character.ListTreasurePort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListTreasurePort::class])
class TreasureList(override val repository: TreasureRepositoryPort) :
    GetList<TreasureQuery, TreasureModel>(), ListTreasurePort {
    override fun getCacheKey(query: TreasureQuery): String {
        query as TreasureQuery.All
        return "model_Treasure:all:${query.page}:${query.size}"
    }
}
