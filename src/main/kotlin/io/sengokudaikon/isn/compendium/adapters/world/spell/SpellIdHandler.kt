package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdSpellPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellIdHandler :
    ByIdHandler<SpellModel, SpellQuery.ById, ByIdSpellPort>() {
    override val useCase: ByIdSpellPort by inject()
    override fun createQuery(id: String, secondaryId: String?): SpellQuery.ById {
        return SpellQuery.ById(id)
    }
}
