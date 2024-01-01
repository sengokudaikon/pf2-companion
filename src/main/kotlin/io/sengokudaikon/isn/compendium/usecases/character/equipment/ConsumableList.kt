package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ConsumableRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.ports.character.ListConsumablePort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListConsumablePort::class])
class ConsumableList(override val repository: ConsumableRepositoryPort) :
    GetList<ConsumableQuery, ConsumableModel>(), ListConsumablePort {
    override fun getCacheKey(query: ConsumableQuery): String {
        query as ConsumableQuery.All
        return "model_Consumable:all:${query.page}:${query.size}"
    }
}
