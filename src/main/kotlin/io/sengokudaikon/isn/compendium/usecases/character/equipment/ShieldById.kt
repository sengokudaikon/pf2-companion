package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ShieldRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdShieldPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdShieldPort::class])
class ShieldById(override val repository: ShieldRepositoryPort) :
    GetById<ShieldQuery, ShieldModel, ShieldRepositoryPort>(), ByIdShieldPort {
    override fun getCacheKey(query: ShieldQuery): String {
        query as ShieldQuery.ById
        return "model_shieldmodel:id:${query.id}"
    }
}
