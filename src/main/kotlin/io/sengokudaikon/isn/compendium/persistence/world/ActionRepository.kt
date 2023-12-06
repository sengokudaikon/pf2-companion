package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
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
    override suspend fun findByName(name: String): Result<ActionModel> = super.findByName(name).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }

    override suspend fun findById(id: String): Result<ActionModel> = super.findById(id).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }
}
