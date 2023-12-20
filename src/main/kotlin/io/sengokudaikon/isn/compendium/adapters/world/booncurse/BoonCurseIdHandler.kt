package io.sengokudaikon.isn.compendium.adapters.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdBoonCursePort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BoonCurseIdHandler :
    ByIdHandler<BoonCurseModel, BoonCurseQuery.ById, ByIdBoonCursePort>() {
    override val useCase: ByIdBoonCursePort by inject()
    override fun createQuery(id: String, secondaryId: String?): BoonCurseQuery.ById {
        return BoonCurseQuery.ById(id)
    }
}
