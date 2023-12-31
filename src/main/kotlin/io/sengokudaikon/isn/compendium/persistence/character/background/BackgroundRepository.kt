package io.sengokudaikon.isn.compendium.persistence.character.background

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BackgroundRepositoryPort::class])
class BackgroundRepository(
) : BaseRepository<BackgroundModel>(), BackgroundRepositoryPort {
    override val modelClass: KClass<BackgroundModel> = BackgroundModel::class
    override val collection: MongoCollection<BackgroundModel> =
        DatabaseFactory.database.getCollection<BackgroundModel>("backgrounds")

    private val aggregationParameters = listOf(
        AggregationParameter(
            "feats",
            "items",
            "name",
            "feats",
            additionalFields = mapOf(
                "items" to """
                    {
                        "${'$'}map": {
                            "input": { "${'$'}objectToArray": "${'$'}system.items" },
                            "as": "item",
                            "in": {
                                "${'$'}cond": {
                                    "if": { "${'$'}eq": [ { "${'$'}arrayElemAt": [ { "${'$'}split": ["${'$'}${'$'}item.v.uuid", "."] }, 1 ] }, "feats" ] },
                                    "then": { "${'$'}arrayElemAt": [ { "${'$'}split": ["${'$'}${'$'}item.v.uuid", "."] }, -1 ] },
                                    "else": "${'$'}undefined"
                                }
                            }
                        }
                    }
                """
            )
        )
    )
    override suspend fun findByName(name: String, criteria: Criteria): Result<BackgroundModel> = aggregatedFind(
        aggregationParameters,
        criteria.addCondition(eq("name", name))
    )

    override suspend fun findById(id: String, criteria: Criteria): Result<BackgroundModel> = aggregatedFind(
        aggregationParameters,
        Criteria().addCondition(eq("id", id))
    )

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<BackgroundModel>> = aggregatedList(
        aggregationParameters,
        criteria,
        page,
        limit
    )
}
