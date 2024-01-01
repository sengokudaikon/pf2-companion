package io.sengokudaikon.isn.compendium.adapters.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdBestiaryPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BestiaryIdHandler :
    ByIdHandler<BestiaryModel, BestiaryQuery.ById, ByIdBestiaryPort>() {
    override val useCase: ByIdBestiaryPort by inject()
    override fun createQuery(id: String): BestiaryQuery.ById {
        return BestiaryQuery.ById(id)
    }
}
