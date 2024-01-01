package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdArmorPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ArmorIdHandler :
    ByIdHandler<ArmorModel, ArmorQuery.ById, ByIdArmorPort>() {
    override val useCase: ByIdArmorPort by inject()
    override fun createQuery(id: String): ArmorQuery.ById {
        return ArmorQuery.ById(id)
    }
}
