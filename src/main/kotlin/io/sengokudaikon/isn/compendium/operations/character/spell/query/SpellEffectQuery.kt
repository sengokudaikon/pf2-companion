package io.sengokudaikon.isn.compendium.operations.character.spell.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface SpellEffectQuery : Query {
    @Resource("/api/spellseffects")
    data class All(override val page: Int, override val size: Int) :
        Query.All<List<SpellEffectModel>>,
        SpellEffectQuery {
        override var filters: String? = null
        override var sort: String? = null }

    @Resource("/api/spellseffect/{id}")
    data class ById(override val id: String) : Query.ById<SpellEffectModel>, SpellEffectQuery

    @Resource("/api/spellseffect")
    data class ByName(override val name: String) : Query.ByName<SpellEffectModel>, SpellEffectQuery
}
