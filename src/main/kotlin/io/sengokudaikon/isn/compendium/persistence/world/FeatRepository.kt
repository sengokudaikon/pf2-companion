package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single
class FeatRepository : BaseRepository<FeatModel>(FeatModel::class), FeatRepositoryPort {
    override val collection: MongoCollection<FeatModel> = DatabaseFactory.database.getCollection<FeatModel>("feats")
}
