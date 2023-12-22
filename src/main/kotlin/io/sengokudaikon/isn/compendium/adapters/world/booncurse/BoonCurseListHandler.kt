package io.sengokudaikon.isn.compendium.adapters.world.booncurse

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.ports.world.ListBoonCursePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BoonCurseListHandler :
    ListHandler<BoonCurseModel, BoonCurseQuery.All, ListBoonCursePort>() {
    override val useCase: ListBoonCursePort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): BoonCurseQuery.All {
        return BoonCurseQuery.All(page, size).apply {
            this.filters = filters
            this.sort = sort
        }
    }
}
