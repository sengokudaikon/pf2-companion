package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ShieldRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ShieldRepositoryPort::class])
class ShieldRepository : BaseRepository<ShieldModel>(), ShieldRepositoryPort {
    override val collection: MongoCollection<ShieldModel> = getCollection("equipments")
    override val modelClass: KClass<ShieldModel> = ShieldModel::class
}
