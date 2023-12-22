package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdArmorPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdArmorPort::class])
class ArmorById(override val repository: ArmorRepositoryPort) :
    GetById<ArmorQuery, ArmorModel>(), ByIdArmorPort {
    override fun getCacheKey(query: ArmorQuery): String {
        query as ArmorQuery.ById
        return "model_model.armormodel:id:${query.id}"
    }
}
