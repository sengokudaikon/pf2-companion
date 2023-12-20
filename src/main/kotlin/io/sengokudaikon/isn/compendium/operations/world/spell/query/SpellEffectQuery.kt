package io.sengokudaikon.isn.compendium.operations.world.spell.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface SpellEffectQuery : Query {
    @Resource("/api/spells/{id}/effects/list/{page}/{size}")
    data class All(val id: String, override val page: Int, override val size: Int) :
        Query.All<List<SpellEffectModel>>,
        SpellQuery

    @Resource("/api/spells/{id}/effects/{secondaryId}")
    data class ById(override val id: String, val effectId: String) : Query.ById<SpellEffectModel>, SpellQuery

    @Resource("/api/spells/{id}/effects/name/{name}")
    data class ByName(val id: String, override val name: String) : Query.ByName<SpellEffectModel>, SpellQuery
}
