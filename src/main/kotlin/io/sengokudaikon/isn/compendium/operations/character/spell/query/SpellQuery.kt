package io.sengokudaikon.isn.compendium.operations.character.spell.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface SpellQuery : Query {
    @Resource("/api/spells/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<SpellModel>>, SpellQuery

    @Resource("/api/spells/{id}")
    data class ById(override val id: String) : Query.ById<SpellModel>, SpellQuery

    @Resource("/api/spells/name/{name}")
    data class ByName(override val name: String) : Query.ByName<SpellModel>, SpellQuery
}
