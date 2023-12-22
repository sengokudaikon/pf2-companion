package io.sengokudaikon.isn.compendium.persistence.world.bestiary

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BestiaryRepositoryPort::class])
class BestiaryRepository : BaseRepository<BestiaryModel>(), BestiaryRepositoryPort {
    override val modelClass: KClass<BestiaryModel> = BestiaryModel::class
    override val collection: MongoCollection<BestiaryModel> = getCollection("bestiary")
}
