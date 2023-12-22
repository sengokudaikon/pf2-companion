package io.sengokudaikon.isn.compendium.usecases.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.domain.booncurse.repository.BoonCurseRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ListBoonCursePort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListBoonCursePort::class])
class BoonCurseList(override val repository: BoonCurseRepositoryPort) :
    GetList<BoonCurseQuery, BoonCurseModel>(), ListBoonCursePort {
    override fun getCacheKey(query: BoonCurseQuery): String {
        query as BoonCurseQuery.All
        return "model_booncurse:all:${query.page}:${query.size}"
    }
}