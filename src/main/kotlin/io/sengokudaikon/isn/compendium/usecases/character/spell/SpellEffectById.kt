package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellEffectRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdSpellEffectPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdSpellEffectPort::class])
class SpellEffectById(override val repository: SpellEffectRepositoryPort) :
    GetById<SpellEffectQuery.ById, SpellEffectModel>(), ByIdSpellEffectPort {
    override fun getCacheKey(query: SpellEffectQuery.ById): String {
        query
        return "model_spelleffect:id:${query.id}"
    }
}