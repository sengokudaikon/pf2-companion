package io.sengokudaikon.isn.compendium.persistence.world.condition

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.domain.condition.repository.ConditionRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [ConditionRepositoryPort::class])
class ConditionRepository : BaseRepository<ConditionModel>(), ConditionRepositoryPort {
    override val collection: MongoCollection<ConditionModel> = getCollection("conditions")
    override val modelClass: KClass<ConditionModel> = ConditionModel::class
}
