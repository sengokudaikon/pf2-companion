package io.sengokudaikon.isn.compendium.persistence.items

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class EquipmentRepository : BaseRepository<EquipmentModel>(EquipmentModel::class), EquipmentRepositoryPort {
    override val collection: MongoCollection<EquipmentModel> = getCollection("equipment")
}
