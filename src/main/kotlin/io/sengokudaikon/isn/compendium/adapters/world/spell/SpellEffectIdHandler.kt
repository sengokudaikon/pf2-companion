package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdSpellEffectPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellEffectIdHandler :
    ByIdHandler<SpellEffectModel, SpellEffectQuery.ById, ByIdSpellEffectPort>() {
    override val useCase: ByIdSpellEffectPort by inject()
    override fun createQuery(id: String): SpellEffectQuery.ById {
        return SpellEffectQuery.ById(id)
    }
}
