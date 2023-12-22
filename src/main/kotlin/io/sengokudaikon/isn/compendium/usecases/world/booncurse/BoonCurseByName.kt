package io.sengokudaikon.isn.compendium.usecases.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.domain.booncurse.repository.BoonCurseRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameBoonCursePort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameBoonCursePort::class])
class BoonCurseByName(override val repository: BoonCurseRepositoryPort) :
    GetByName<BoonCurseQuery, BoonCurseModel>(), ByNameBoonCursePort {
    override fun getCacheKey(query: BoonCurseQuery): String {
        query as BoonCurseQuery.ByName
        return "model_booncurse:name:${query.name}"
    }
}