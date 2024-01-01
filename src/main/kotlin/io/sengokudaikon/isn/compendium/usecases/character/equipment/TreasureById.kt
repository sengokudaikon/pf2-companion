package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.TreasureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdTreasurePort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdTreasurePort::class])
class TreasureById(override val repository: TreasureRepositoryPort) :
    GetById<TreasureQuery, TreasureModel>(), ByIdTreasurePort {
    override fun getCacheKey(query: TreasureQuery): String {
        query as TreasureQuery.ById
        return "model_treasure:id:${query.id}"
    }
}
