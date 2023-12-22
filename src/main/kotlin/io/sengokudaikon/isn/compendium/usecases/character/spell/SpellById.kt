package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdSpellPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdSpellPort::class])
class SpellById(override val repository: SpellRepositoryPort) : GetById<SpellQuery, SpellModel>(),
    ByIdSpellPort {
    override fun getCacheKey(query: SpellQuery): String {
        query as SpellQuery.ById
        return "model_spell:id:${query.id}"
    }
}