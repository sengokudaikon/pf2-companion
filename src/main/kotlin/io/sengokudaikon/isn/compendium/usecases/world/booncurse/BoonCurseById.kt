package io.sengokudaikon.isn.compendium.usecases.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.domain.booncurse.repository.BoonCurseRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdBoonCursePort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdBoonCursePort::class])
class BoonCurseById(override val repository: BoonCurseRepositoryPort) :
    GetById<BoonCurseQuery, BoonCurseModel>(), ByIdBoonCursePort {
    override fun getCacheKey(query: BoonCurseQuery): String {
        query as BoonCurseQuery.ById
        return "model_booncurse:id:${query.id}"
    }
}