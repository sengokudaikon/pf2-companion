package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameArmorPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ArmorNameHandler : ByNameHandler<ArmorModel, ArmorQuery.ByName, ByNameArmorPort>() {
    override val useCase: ByNameArmorPort by inject()
    override fun createQuery(name: String, id: String?): ArmorQuery.ByName {
        return ArmorQuery.ByName(name = name)
    }
}
