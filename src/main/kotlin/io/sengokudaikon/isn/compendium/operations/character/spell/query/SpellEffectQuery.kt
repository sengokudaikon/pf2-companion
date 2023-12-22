package io.sengokudaikon.isn.compendium.operations.character.spell.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface SpellEffectQuery : Query {
    @Resource("/api/spellseffects/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) :
        Query.All<List<SpellEffectModel>>,
        SpellEffectQuery

    @Resource("/api/spellseffects/{id}")
    data class ById(override val id: String) : Query.ById<SpellEffectModel>, SpellEffectQuery

    @Resource("/api/spellseffects/name/{name}")
    data class ByName(override val name: String) : Query.ByName<SpellEffectModel>, SpellEffectQuery
}
