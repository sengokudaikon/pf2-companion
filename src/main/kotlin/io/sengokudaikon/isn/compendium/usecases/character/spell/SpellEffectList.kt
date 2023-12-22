package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellEffectRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.character.ListSpellEffectPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListSpellEffectPort::class])
class SpellEffectList(override val repository: SpellEffectRepositoryPort) :
    GetList<SpellEffectQuery.All, SpellEffectModel>(),
    ListSpellEffectPort {
    override fun getCacheKey(query: SpellEffectQuery.All): String {
        query
        return "model_spelleffect:all:${query.page}:${query.size}"
    }
}