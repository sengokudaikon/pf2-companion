package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
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
        }.map { it.name }
        val features = featureRepository.findByNames(
            featNames
        ).getOrDefault(emptyList())
        classModel.features = features
        return classModel
    }

    override suspend fun findAll(page: Int, limit: Int): Result<List<ClassModel>> = runCatching {
        val classes = super.findAll(page, limit).getOrDefault(emptyList())
        classes.map {
            withFeats(it)
        }.toList()
    }

    override suspend fun findById(id: String): Result<ClassModel> {
        return super.findById(id).map { withFeats(it) }
    }

    override suspend fun findByName(name: String): Result<ClassModel> {
        return super.findByName(name).map { withFeats(it) }
    }
}
