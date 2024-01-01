package io.sengokudaikon.isn.compendium.persistence.character.companion

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.companion.AnimalCompanionModel
import io.sengokudaikon.isn.compendium.domain.companion.repository.AnimalCompanionRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single
class AnimalCompanionRepository : BaseRepository<AnimalCompanionModel>(), AnimalCompanionRepositoryPort {
    override val collection: MongoCollection<AnimalCompanionModel> = getCollection("animalcompanions")
    override val modelClass: KClass<AnimalCompanionModel> = AnimalCompanionModel::class
}
