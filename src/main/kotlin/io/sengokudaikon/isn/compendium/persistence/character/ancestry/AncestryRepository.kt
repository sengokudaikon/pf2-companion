package io.sengokudaikon.isn.compendium.persistence.character.ancestry

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.`in`
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [AncestryRepositoryPort::class])
class AncestryRepository(
) : BaseRepository<AncestryModel>(), AncestryRepositoryPort {
    override val modelClass: KClass<AncestryModel> = AncestryModel::class
    override val collection: MongoCollection<AncestryModel> =
        DatabaseFactory.database.getCollection<AncestryModel>("ancestries")

    val ancestryFeatures = listOf(
        AggregationParameter(
            "ancestryfeatures",
            "items",
            "name",
            "ancestryFeatures",
            additionalFields = mapOf(
                "items" to """
                    {
                        "${"$"}map": {
                            "input": { "${"$"}objectToArray": "${"$"}system.items" },
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
    val heritages = listOf(
        AggregationParameter(
            "heritages",
            "name",
            "system.ancestry.name",
            "heritages"
        )
    )

    override suspend fun findByName(name: String, criteria: Criteria): Result<AncestryModel> = aggregatedFind(
        ancestryFeatures + heritages,
        criteria.addCondition(eq("name", name))
    ).also {
        it.getOrNull()?.let { ancestry ->
            ancestry.system.rules
            }
        }

    override suspend fun findById(id: String, criteria: Criteria): Result<AncestryModel> = aggregatedFind(
        ancestryFeatures + heritages,
        Criteria().addCondition(eq("id", id))
    )

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<AncestryModel>> = aggregatedList(
        ancestryFeatures + heritages,
        criteria,
        page,
        limit
    )

    override suspend fun findByNames(names: List<String>): Result<List<AncestryModel>> =
        aggregatedList(
            ancestryFeatures + heritages,
            Criteria().addCondition(`in`("name", names)),
            1,
            100
        )
}
