package io.sengokudaikon.isn.compendium.persistence.world.booncurse

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.domain.booncurse.repository.BoonCurseRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [BoonCurseRepositoryPort::class])
class BoonCurseRepository : BaseRepository<BoonCurseModel>(), BoonCurseRepositoryPort {
    override val collection: MongoCollection<BoonCurseModel> = getCollection("booncurse")
    override val modelClass: KClass<BoonCurseModel>
        get() = TODO("Not yet implemented")
}