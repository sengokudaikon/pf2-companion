package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.BackpackRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdBackpackPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdBackpackPort::class])
class BackpackById(override val repository: BackpackRepositoryPort) :
    GetById<BackpackQuery, BackpackModel>(), ByIdBackpackPort {
    override fun getCacheKey(query: BackpackQuery): String {
        query as BackpackQuery.ById
        return "model_backpack:id:${query.id}"
    }
}
