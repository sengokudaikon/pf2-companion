package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.KitRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdKitPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdKitPort::class])
class KitById(override val repository: KitRepositoryPort) :
    GetById<KitQuery, KitModel>(), ByIdKitPort {
    override fun getCacheKey(query: KitQuery): String {
        query as KitQuery.ById
        return "model_kit:id:${query.id}"
    }
}
