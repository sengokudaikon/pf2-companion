package io.sengokudaikon.isn.compendium.persistence.character.equipment

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.ArmorRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ArmorRepositoryPort::class])
class ArmorRepository: ArmorRepositoryPort, BaseRepository<ArmorModel>() {
    override val collection: MongoCollection<ArmorModel> = getCollection("equipments")
    override val modelClass: KClass<ArmorModel> = ArmorModel::class

    override suspend fun findByName(name: String): Result<ArmorModel> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: String): Result<ArmorModel> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(page: Int, limit: Int): Result<List<ArmorModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun findByNames(names: List<String>): Result<List<ArmorModel>> {
        TODO("Not yet implemented")
    }
}
