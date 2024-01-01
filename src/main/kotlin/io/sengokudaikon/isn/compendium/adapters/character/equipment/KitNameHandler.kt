package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameKitPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class KitNameHandler :
    ByNameHandler<KitModel, KitQuery.ByName, ByNameKitPort>() {
    override val useCase: ByNameKitPort by inject()
    override fun createQuery(name: String, id: String?): KitQuery.ByName {
        return KitQuery.ByName(name)
    }
}
