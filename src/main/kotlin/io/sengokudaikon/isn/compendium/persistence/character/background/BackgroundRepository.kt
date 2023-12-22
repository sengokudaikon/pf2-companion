package io.sengokudaikon.isn.compendium.persistence.character.background

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.GeneralFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BackgroundRepositoryPort::class])
class BackgroundRepository(
    private val featRepository: GeneralFeatRepositoryPort,
    private val actionRepository: ActionRepositoryPort,
) : BaseRepository<BackgroundModel>(), BackgroundRepositoryPort {
    override val modelClass: KClass<BackgroundModel> = BackgroundModel::class
    override val collection: MongoCollection<BackgroundModel> =
        DatabaseFactory.database.getCollection<BackgroundModel>("backgrounds")

    private suspend fun <T : Model> findItems(
        background: BackgroundModel,
        uuidKeyword: String,
        repository: RepositoryOutputPort<T>
    ): Result<Map<String, T>> =
        runCatching {
            val itemNames = background.system.items.values.filter { it.uuid.contains(uuidKeyword) }
                .map { it.uuid.split(".").last() }

            if (itemNames.isEmpty()) {
                return@runCatching emptyMap()
            }
            val items = repository.findByNames(itemNames).getOrDefault(emptyList()).associateBy { it.name }
            items
        }

    private suspend fun findFeats(background: BackgroundModel): Result<Map<String, FeatModel>> =
        findItems(background, "feats", featRepository)

    private suspend fun findActions(background: BackgroundModel): Result<Map<String, ActionModel>> =
        findItems(background, "actions", actionRepository)

    override suspend fun findByName(name: String, filters: List<Filter>): Result<BackgroundModel> = super.findByName(name, filters).map {
        it.feats = findFeats(it).getOrDefault(emptyMap())
        it.actions = findActions(it).getOrDefault(emptyMap())
        it
    }

    override suspend fun findById(id: String, filters: List<Filter>): Result<BackgroundModel> = super.findById(id, filters).map {
        it.feats = findFeats(it).getOrDefault(emptyMap())
        it.actions = findActions(it).getOrDefault(emptyMap())
        it
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        filters: List<Filter>,
        sort: List<Sort>
    ): Result<List<BackgroundModel>> = runCatching {
        val backgrounds = super.findAll(page, limit, filters, sort).getOrDefault(emptyList())
        backgrounds.map {
            it.feats = findFeats(it).getOrDefault(emptyMap())
            it.actions = findActions(it).getOrDefault(emptyMap())
            it
        }.toList()
    }
}
