package io.sengokudaikon.isn.compendium.persistence.character.action

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import kotlinx.coroutines.flow.toList
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ActionRepositoryPort::class])
class ActionRepository : BaseRepository<ActionModel>(), ActionRepositoryPort {
    override val modelClass: KClass<ActionModel> = ActionModel::class
    override var collection: MongoCollection<ActionModel> = getCollection<ActionModel>("actions")
    private fun featEffects(actions: List<ActionModel>): MutableList<AggregationParameter> {
        val params = mutableListOf<AggregationParameter>()
        actions.forEach {
            if (it.system.selfEffect !== null) {
                params.add(
                    AggregationParameter(
                        "feat-effects",
                        "name",
                        "name",
                        "effect",
                        true
                    )
                )
            }
        }
        return params
    }
    override suspend fun findByName(name: String, criteria: Criteria): Result<ActionModel> {
        criteria.addCondition(eq("name", name))
        val documents = collection.find(criteria.build()).toList()
        val params = featEffects(documents)
        return aggregatedFind(params, criteria)
    }

    override suspend fun findById(id: String, criteria: Criteria): Result<ActionModel> {
        criteria.addCondition(eq("id", id))
        val documents = collection.find(criteria.build()).toList()
        val params = featEffects(documents)
        return aggregatedFind(params, criteria)
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria,
    ): Result<List<ActionModel>> {
        val documents = collection.find(criteria.build()).toList()
        val params = featEffects(documents)
        return aggregatedList(params, criteria, page, limit)
    }
}
