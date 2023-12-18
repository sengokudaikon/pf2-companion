package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ListArmorPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ArmorListHandler : ListHandler<List<ArmorModel>, ArmorQuery.All, ListArmorPort>() {
    override val useCase: ListArmorPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): ArmorQuery.All {
        return ArmorQuery.All(page, size)
    }
}
