package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class ClassFeatureRepository : BaseRepository<ClassFeatureModel>(ClassFeatureModel::class), ClassFeatureRepositoryPort {
    override val collection: MongoCollection<ClassFeatureModel> = getCollection("class-features")
}
