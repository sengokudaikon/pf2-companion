package io.sengokudaikon.isn.compendium.persistence.character.background

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single(binds = [BackgroundRepositoryPort::class])
class BackgroundRepository(
    private val featRepository: FeatRepositoryPort,
    private val actionRepository: ActionRepositoryPort,
) : BaseRepository<BackgroundModel>(kClass = BackgroundModel::class), BackgroundRepositoryPort {
    override val collection: MongoCollection<BackgroundModel> =
        DatabaseFactory.database.getCollection<BackgroundModel>("backgrounds")

    private suspend fun findFeats(background: BackgroundModel): Result<Map<String, FeatModel>> =
        runCatching {
            val featNames = background.system.items.values.filter { it.uuid.contains("Compendium.pf2.feats-*") }
                .map { it.name }
            val feats = featRepository.findByNames(featNames).getOrNull()?.associateBy { it.name }
                ?: throw DatabaseException.NotFound(FeatModel::class.qualifiedName)

            feats
        }

    private suspend fun findActions(background: BackgroundModel): Result<Map<String, ActionModel>> =
        runCatching {
            val actions = background.system.items.values
                .map { item ->
                    actionRepository.findByName(item.name).getOrNull()
                        ?: throw DatabaseException.NotFound(ActionModel::class.qualifiedName)
                }.associateBy { it.name }
            actions
        }

    override suspend fun findByName(name: String): Result<BackgroundModel> = super.findByName(name).map {
        it.feats = findFeats(it).getOrDefault(emptyMap())
        it.actions = findActions(it).getOrDefault(emptyMap())
        it
    }

    override suspend fun findById(id: String): Result<BackgroundModel> = super.findById(id).map {
        it.feats = findFeats(it).getOrDefault(emptyMap())
        it.actions = findActions(it).getOrDefault(emptyMap())
        it
    }
}
