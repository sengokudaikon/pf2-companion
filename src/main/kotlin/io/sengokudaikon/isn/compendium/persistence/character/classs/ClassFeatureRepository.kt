package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.infrastructure.mapper.extractValue
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.operations.transform
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ClassFeatureRepositoryPort::class])
class ClassFeatureRepository(
    val classRepository: ClassRepositoryPort
) : BaseRepository<ClassFeatureModel>(),
    ClassFeatureRepositoryPort {
    override val modelClass: KClass<ClassFeatureModel> = ClassFeatureModel::class
    override val collection: MongoCollection<ClassFeatureModel> = getCollection("classfeatures")

    override suspend fun findByNames(names: List<String>): Result<List<ClassFeatureModel>> {
        val result = super.findByNames(names)
        return result.map { it.sortedBy { it.system.level?.transform().extractValue()?.toString()?.toInt() } }
    }
}
