package io.sengokudaikon.isn.compendium.persistence.character.classs

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ClassRepositoryPort::class])
class ClassRepository(
    private val featureRepository: ClassFeatureRepositoryPort
) : BaseRepository<ClassModel>(), ClassRepositoryPort {
    override val modelClass: KClass<ClassModel> = ClassModel::class
    override val collection: MongoCollection<ClassModel> = getCollection("classes")

    private val classFeatures= listOf(
        AggregationParameter(
            "classfeatures",
            "items",
            "name",
            "features",
            additionalFields = mapOf(
                "items" to """
                        {
                            "${"$"}map": {
                                "input": { "${"$"}objectToArray": "${"$"}system.items" },
                                "as": "item",
                                "in": {
                                    "${'$'}cond": {
                                        "if": { "${'$'}eq": [ { "${'$'}arrayElemAt": [ { "${'$'}split": ["${'$'}${'$'}item.v.uuid", "."] }, 1 ] }, "classfeatures" ] },
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

    override suspend fun findAll(
        page: Int,
        limit: Int,
        criteria: Criteria
    ): Result<List<ClassModel>> = aggregatedList(
        classFeatures,
        criteria,
        page,
        limit
    )

    override suspend fun findById(id: String, criteria: Criteria): Result<ClassModel> = aggregatedFind(
        classFeatures,
        criteria.addCondition(eq("id", id))
    )

    override suspend fun findByName(name: String, criteria: Criteria): Result<ClassModel> = aggregatedFind(
        classFeatures,
        criteria.addCondition(eq("name", name))
    )
}
