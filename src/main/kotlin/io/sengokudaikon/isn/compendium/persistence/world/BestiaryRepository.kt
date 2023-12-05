package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class BestiaryRepository : BaseRepository<BestiaryModel>(BestiaryModel::class), BestiaryRepositoryPort {
    override val collection: MongoCollection<BestiaryModel> = getCollection("bestiary")
}
