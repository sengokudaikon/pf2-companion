package io.sengokudaikon.isn.compendium.adapters.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameBoonCursePort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BoonCurseNameHandler :
    ByNameHandler<BoonCurseModel, BoonCurseQuery.ByName, ByNameBoonCursePort>() {
    override val useCase: ByNameBoonCursePort by inject()
    override fun createQuery(name: String, id: String?): BoonCurseQuery.ByName {
        return BoonCurseQuery.ByName(name)
    }
}
