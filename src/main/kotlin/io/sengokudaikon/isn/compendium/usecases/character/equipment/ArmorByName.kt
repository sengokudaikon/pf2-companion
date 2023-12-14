package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameArmorPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class ArmorByName(override val repository: ArmorRepositoryPort) :
    GetByName<ArmorQuery, ArmorModel, ArmorRepositoryPort>(), ByNameArmorPort {
    override fun getCacheKey(query: ArmorQuery): String {
        query as ArmorQuery.ByName
        return "model_armor:name:${query.name}"
    }
}
