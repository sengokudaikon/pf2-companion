package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ShieldRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ListShieldPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListShieldPort::class])
class ShieldList(override val repository: ShieldRepositoryPort) :
    GetList<ShieldQuery, ShieldModel>(), ListShieldPort {
    override fun getCacheKey(query: ShieldQuery): String {
        query as ShieldQuery.All
        return "model_shield:all:${query.page}:${query.size}"
    }
}
