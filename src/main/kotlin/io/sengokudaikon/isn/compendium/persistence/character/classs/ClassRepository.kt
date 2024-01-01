package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ClassRepositoryPort::class])
class ClassRepository(
    private val featureRepository: ClassFeatureRepositoryPort
) : BaseRepository<ClassModel>(), ClassRepositoryPort {
    override val modelClass: KClass<ClassModel> = ClassModel::class
    override val collection: MongoCollection<ClassModel> = getCollection("classes")

    private suspend fun withFeats(classModel: ClassModel): ClassModel {
        val featNames = classModel.system.items.values.filter {
            it.uuid.contains("classfeatures")
        }.map { it.uuid.split(".").last() }
        val features = featureRepository.findByNames(
            featNames
        ).getOrDefault(emptyList())
        require(features.size == featNames.size)
        classModel.features = features
        return classModel
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria,
    ): Result<List<ClassModel>> = runCatching {
        val classes = super.findAll(page, limit, criteria).getOrDefault(emptyList())
        classes.map {
            withFeats(it)
        }.toList()
    }

    override suspend fun findById(id: String,criteria: Criteria): Result<ClassModel> {
        return super.findById(id, criteria).map { withFeats(it) }
    }

    override suspend fun findByName(name: String,criteria: Criteria): Result<ClassModel> {
        return super.findByName(name, criteria).map { withFeats(it) }
    }
}
