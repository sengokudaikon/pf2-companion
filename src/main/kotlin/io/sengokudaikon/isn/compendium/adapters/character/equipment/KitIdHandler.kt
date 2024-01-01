package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.ports.character.ByIdKitPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class KitIdHandler :
    ByIdHandler<KitModel, KitQuery.ById, ByIdKitPort>() {
    override val useCase: ByIdKitPort by inject()
    override fun createQuery(id: String): KitQuery.ById {
        return KitQuery.ById(id)
    }
}
