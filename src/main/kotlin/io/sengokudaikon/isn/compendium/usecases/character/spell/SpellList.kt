package io.sengokudaikon.isn.compendium.usecases.character.spell

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.ports.character.ListSpellPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListSpellPort::class])
class SpellList(override val repository: SpellRepositoryPort) :
    GetList<SpellQuery, SpellModel>(), ListSpellPort {
    override fun getCacheKey(query: SpellQuery): String {
        query as SpellQuery.All
        return "model_spell:all:${query.page}:${query.size}"
    }
}