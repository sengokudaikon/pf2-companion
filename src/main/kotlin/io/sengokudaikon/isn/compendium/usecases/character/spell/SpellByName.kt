package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameSpellPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameSpellPort::class])
class SpellByName(override val repository: SpellRepositoryPort) :
    GetByName<SpellQuery, SpellModel>(), ByNameSpellPort {
    override fun getCacheKey(query: SpellQuery): String {
        query as SpellQuery.ByName
        return "model_spell:name:${query.name}"
    }
}