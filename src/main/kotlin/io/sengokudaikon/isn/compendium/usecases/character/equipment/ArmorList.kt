package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ListArmorPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListArmorPort::class])
class ArmorList(override val repository: ArmorRepositoryPort) :
    GetList<ArmorQuery, ArmorModel, List<ArmorModel>, ArmorRepositoryPort>(), ListArmorPort {
    override fun getCacheKey(query: ArmorQuery): String {
        query as ArmorQuery.All
        return "model_armor:all:${query.page}:${query.size}"
    }
}
