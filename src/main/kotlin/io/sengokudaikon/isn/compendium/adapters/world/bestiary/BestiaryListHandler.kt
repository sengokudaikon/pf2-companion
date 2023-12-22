package io.sengokudaikon.isn.compendium.adapters.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ListBestiaryPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BestiaryListHandler :
    ListHandler<List<BestiaryModel>, BestiaryQuery.All, ListBestiaryPort>() {
    override val useCase: ListBestiaryPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, id: String?): BestiaryQuery.All {
        return BestiaryQuery.All(page, size, filters)
    }
}
