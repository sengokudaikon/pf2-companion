package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.world.ListSpellPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellListHandler :
    ListHandler<List<SpellModel>, SpellQuery.All, ListSpellPort>() {
    override val useCase: ListSpellPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): SpellQuery.All {
        return SpellQuery.All(page, size)
    }
}