package io.sengokudaikon.isn.compendium.persistence.character.feat

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [FeatRepositoryPort::class])
class FeatRepository : BaseRepository<FeatModel>(), FeatRepositoryPort {
    override val modelClass: KClass<FeatModel> = FeatModel::class
    override val collection: MongoCollection<FeatModel> = DatabaseFactory.database.getCollection<FeatModel>("feats")
}
