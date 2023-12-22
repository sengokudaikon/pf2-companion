package io.sengokudaikon.isn.compendium.operations.character.spell.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface SpellQuery : Query {
    @Resource("/api/spells")
    data class All(override val page: Int, override val size: Int) : Query.All<List<SpellModel>>, SpellQuery {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/spell/{id}")
    data class ById(override val id: String) : Query.ById<SpellModel>, SpellQuery

    @Resource("/api/spell")
    data class ByName(override val name: String) : Query.ByName<SpellModel>, SpellQuery
}
