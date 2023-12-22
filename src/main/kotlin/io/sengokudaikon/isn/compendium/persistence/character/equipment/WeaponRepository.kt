package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [WeaponRepositoryPort::class])
class WeaponRepository : BaseRepository<WeaponModel>(), WeaponRepositoryPort {
    override val modelClass: KClass<WeaponModel> = WeaponModel::class
    override val collection: MongoCollection<WeaponModel> = getCollection("equipments")
}
