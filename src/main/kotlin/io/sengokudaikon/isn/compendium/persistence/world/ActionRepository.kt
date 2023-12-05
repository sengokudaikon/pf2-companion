package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.koin.core.annotation.Single

@Single(binds = [ActionRepositoryPort::class])
class ActionRepository(
    private val effectRepositoryPort: FeatEffectsRepository,
) : BaseRepository<ActionModel>(ActionModel::class), ActionRepositoryPort {
    override var collection: MongoCollection<ActionModel> = getCollection<ActionModel>("actions")

    private suspend fun findEffects(action: ActionModel): Result<FeatEffectModel?> =
        runCatching {
            val effect = action.system.selfEffect
                ?.let { effectRepositoryPort.findByName(it.name).getOrNull() }
            effect
        }
    override suspend fun findByName(name: String): Result<ActionModel> = find(ActionModel::name, name).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }

    override suspend fun findById(id: String): Result<ActionModel> = find(ActionModel::id, id).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }

    override suspend fun findAll(page: Int, limit: Int): Result<List<ActionModel>> = runCatching {
        collection.find().skip((page - 1) * limit).limit(limit).toList()
    }

    override suspend fun findAllNames(): Result<List<String>> = runCatching {
        collection.find().map { it.name }.toList()
    }
}
