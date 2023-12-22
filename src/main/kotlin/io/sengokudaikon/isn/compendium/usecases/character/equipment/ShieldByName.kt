package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ShieldRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameShieldPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameShieldPort::class])
class ShieldByName(override val repository: ShieldRepositoryPort) :
    GetByName<ShieldQuery, ShieldModel>(), ByNameShieldPort {
    override fun getCacheKey(query: ShieldQuery): String {
        query as ShieldQuery.ByName
        return "model_shield:name:${query.name}"
    }
}
