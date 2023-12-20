package io.sengokudaikon.isn.compendium.adapters.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameBestiaryPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BestiaryNameHandler :
    ByNameHandler<BestiaryModel, BestiaryQuery.ByName, ByNameBestiaryPort>() {
    override val useCase: ByNameBestiaryPort by inject()
    override fun createQuery(name: String, id: String?): BestiaryQuery.ByName {
        return BestiaryQuery.ByName(name)
    }
}
