package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.BackpackRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameBackpackPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameBackpackPort::class])
class BackpackByName(override val repository: BackpackRepositoryPort) :
    GetByName<BackpackQuery, BackpackModel>(), ByNameBackpackPort {
    override fun getCacheKey(query: BackpackQuery): String {
        query as BackpackQuery.ByName
        return "model_Backpack:name:${query.name}"
    }
}
