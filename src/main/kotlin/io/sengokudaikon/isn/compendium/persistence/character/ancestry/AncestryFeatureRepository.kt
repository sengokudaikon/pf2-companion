package io.sengokudaikon.isn.compendium.persistence.character.ancestry

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryFeatureRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single(binds = [AncestryFeatureRepositoryPort::class])
class AncestryFeatureRepository :
    BaseRepository<AncestryFeatureModel>(AncestryFeatureModel::class),
    AncestryFeatureRepositoryPort {
    override val collection: MongoCollection<AncestryFeatureModel> =
        DatabaseFactory.database.getCollection<AncestryFeatureModel>("ancestryfeatures")
}
