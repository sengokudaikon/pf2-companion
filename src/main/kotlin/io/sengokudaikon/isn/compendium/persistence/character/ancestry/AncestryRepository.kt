package io.sengokudaikon.isn.compendium.persistence.character.ancestry

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.koin.core.annotation.Single

@Single(binds = [AncestryRepositoryPort::class])
class AncestryRepository(
    private val ancestryFeatureRepository: AncestryFeatureRepositoryPort,
) : BaseRepository<AncestryModel>(AncestryModel::class), AncestryRepositoryPort {
    override val collection: MongoCollection<AncestryModel> =
        DatabaseFactory.database.getCollection<AncestryModel>("ancestries")

    private suspend fun fetchAncestryFeatures(ancestry: AncestryModel): Result<Map<String, AncestryFeatureModel>> =
        runCatching {
            val featureNames = ancestry.system.items.values.map { it.name }
            val features = ancestryFeatureRepository.findByNames(featureNames).getOrNull()?.associateBy { it.name }
                ?: throw DatabaseException.NotFound(AncestryFeatureModel::class.qualifiedName)
            features
        }

    override suspend fun findByName(name: String): Result<AncestryModel> = runCatching {
        val entity = find(AncestryModel::name, name).getOrThrow()
        entity.let {
            it.ancestryFeatures = fetchAncestryFeatures(it).getOrDefault(emptyMap())
        }
        entity
    }

    override suspend fun findById(id: String): Result<AncestryModel> = runCatching {
        val entity = find(AncestryModel::id, id).getOrThrow()
        entity.let {
            it.ancestryFeatures = fetchAncestryFeatures(it).getOrDefault(emptyMap())
        }
        entity
    }

    override suspend fun findAll(page: Int, limit: Int): Result<List<AncestryModel>> = runCatching {
        val ancList = collection.find().skip((page - 1) * limit).limit(limit)
        ancList.map {
            it.ancestryFeatures = fetchAncestryFeatures(it).getOrDefault(emptyMap())
            it
        }.toList()
    }

    override suspend fun findAllNames(): Result<List<String>> = runCatching {
        collection.find().map { it.name }.toList()
    }

    override suspend fun findByNames(names: List<String>): Result<List<AncestryModel>> {
        return runCatching {
            val ancList = collection.find().filter { names.contains(it.name) }.map {
                it.ancestryFeatures = fetchAncestryFeatures(it).getOrDefault(emptyMap())
                it
            }.toList()
            ancList
        }
    }
}