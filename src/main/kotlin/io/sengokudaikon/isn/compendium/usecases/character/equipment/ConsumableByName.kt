package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ConsumableRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameConsumablePort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameConsumablePort::class])
class ConsumableByName(override val repository: ConsumableRepositoryPort) :
    GetByName<ConsumableQuery, ConsumableModel>(), ByNameConsumablePort {
    override fun getCacheKey(query: ConsumableQuery): String {
        query as ConsumableQuery.ByName
        return "model_Consumable:name:${query.name}"
    }
}
