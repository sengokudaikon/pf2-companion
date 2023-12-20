package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameSpellPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellNameHandler :
    ByNameHandler<SpellModel, SpellQuery.ByName, ByNameSpellPort>() {
    override val useCase: ByNameSpellPort by inject()
    override fun createQuery(name: String, id: String?): SpellQuery.ByName {
        return SpellQuery.ByName(name)
    }
}
