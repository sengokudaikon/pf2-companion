package io.sengokudaikon.isn.compendium.persistence.character.action

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatEffectsRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ActionRepositoryPort::class])
class ActionRepository(
    private val effectRepositoryPort: FeatEffectsRepositoryPort,
) : BaseRepository<ActionModel>(), ActionRepositoryPort {
    override val modelClass: KClass<ActionModel> = ActionModel::class
    override var collection: MongoCollection<ActionModel> = getCollection<ActionModel>("actions")

    private suspend fun findEffects(action: ActionModel): Result<FeatEffectModel?> =
        runCatching {
            val name = action.system.selfEffect?.name ?: return@runCatching null
            val effect = effectRepositoryPort.findByName(name).getOrNull()
            effect
        }

    override suspend fun findByName(name: String,criteria: Criteria): Result<ActionModel> = super.findByName(name, criteria).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }

    override suspend fun findById(id: String,criteria: Criteria): Result<ActionModel> = super.findById(id, criteria).let {
        it.map {
            it.effect = findEffects(it).getOrNull()
            it
        }
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria,
    ): Result<List<ActionModel>> = runCatching {
        val actionList = super.findAll(page, limit, criteria).getOrThrow()
        actionList.map {
            it.effect = findEffects(it).getOrNull()
            it
        }.toList()
    }
}
