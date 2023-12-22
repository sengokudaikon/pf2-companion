package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellEffectRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellEffectQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameSpellEffectPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameSpellEffectPort::class])
class SpellEffectByName(override val repository: SpellEffectRepositoryPort) :
    GetByName<SpellEffectQuery.ByName, SpellEffectModel>(), ByNameSpellEffectPort {
    override fun getCacheKey(query: SpellEffectQuery.ByName): String {
        query
        return "model_spelleffect:name:${query.name}"
    }
}