package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [EquipmentRepositoryPort::class])
class EquipmentRepository : BaseRepository<EquipmentModel>(), EquipmentRepositoryPort {
    override val modelClass: KClass<EquipmentModel> = EquipmentModel::class
    override val collection: MongoCollection<EquipmentModel> = getCollection("equipment")
}
