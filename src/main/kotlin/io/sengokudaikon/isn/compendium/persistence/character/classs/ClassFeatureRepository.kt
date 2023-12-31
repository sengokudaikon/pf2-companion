package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ClassFeatureRepositoryPort::class])
class ClassFeatureRepository : BaseRepository<ClassFeatureModel>(), ClassFeatureRepositoryPort {
    override val modelClass: KClass<ClassFeatureModel> = ClassFeatureModel::class
    override val collection: MongoCollection<ClassFeatureModel> = getCollection("classfeatures")
}
