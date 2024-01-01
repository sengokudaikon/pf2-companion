package io.sengokudaikon.isn.compendium.persistence.character.ancestry

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.heritage.repository.HeritageRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import kotlinx.coroutines.flow.toList
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [HeritageRepositoryPort::class])
class HeritageRepository: BaseRepository<HeritageModel>(), HeritageRepositoryPort {
    override val collection: MongoCollection<HeritageModel> = getCollection("heritages")
    override val modelClass: KClass<HeritageModel> = HeritageModel::class
    override suspend fun findAllByAncestry(name: String): Result<List<HeritageModel>> = runCatching {
        collection.find(Filters.eq(HeritageModel::system::name.name, name)).toList()
    }
}
