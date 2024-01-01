package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.KitRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.ports.character.ListKitPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListKitPort::class])
class KitList(override val repository: KitRepositoryPort) :
    GetList<KitQuery, KitModel>(), ListKitPort {
    override fun getCacheKey(query: KitQuery): String {
        query as KitQuery.All
        return "model_Kit:all:${query.page}:${query.size}"
    }
}
