package io.sengokudaikon.isn.compendium.adapters.world.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.character.ListSpellEffectPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SpellEffectListHandler :
    ListHandler<SpellEffectModel, SpellEffectQuery.All, ListSpellEffectPort>() {
    override val useCase: ListSpellEffectPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): SpellEffectQuery.All {
        return SpellEffectQuery.All(page, size).apply {
            this.filters = filters
            this.sort = sort
        }
    }
}
