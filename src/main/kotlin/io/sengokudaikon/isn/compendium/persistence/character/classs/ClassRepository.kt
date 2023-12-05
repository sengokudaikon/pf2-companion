package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single
@Suppress("unused")
class ClassRepository(
    private val featureRepository: ClassFeatureRepositoryPort,
) : BaseRepository<ClassModel>(ClassModel::class), ClassRepositoryPort {
    override val collection: MongoCollection<ClassModel> = getCollection("classes")
}
