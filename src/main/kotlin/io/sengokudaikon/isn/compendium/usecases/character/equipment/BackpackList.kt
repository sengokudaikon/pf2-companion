package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.BackpackRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ListBackpackPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListBackpackPort::class])
class BackpackList(override val repository: BackpackRepositoryPort) :
    GetList<BackpackQuery, BackpackModel>(), ListBackpackPort {
    override fun getCacheKey(query: BackpackQuery): String {
        query as BackpackQuery.All
        return "model_Backpack:all:${query.page}:${query.size}"
    }
}
