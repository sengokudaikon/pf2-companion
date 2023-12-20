package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.operations.world.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameSpellEffectPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellEffectNameHandler :
    ByNameHandler<SpellEffectModel, SpellEffectQuery.ByName, ByNameSpellEffectPort>() {
    override val useCase: ByNameSpellEffectPort by inject()
    override fun createQuery(name: String, id: String?): SpellEffectQuery.ByName {
        return SpellEffectQuery.ByName(id!!, name)
    }
}
