package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.KitRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameKitPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameKitPort::class])
class KitByName(override val repository: KitRepositoryPort) :
    GetByName<KitQuery, KitModel>(), ByNameKitPort {
    override fun getCacheKey(query: KitQuery): String {
        query as KitQuery.ByName
        return "model_Kit:name:${query.name}"
    }
}
