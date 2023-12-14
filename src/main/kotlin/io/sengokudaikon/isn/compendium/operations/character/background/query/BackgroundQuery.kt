package io.sengokudaikon.isn.compendium.operations.character.background.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BackgroundQuery : Query {
    @Resource("/api/background/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : BackgroundQuery, Query.All<List<BackgroundModel>>

    @Resource("/api/background/{id}")
    data class ById(override val id: String) : BackgroundQuery, Query.ById<BackgroundModel>

    @Resource("/api/background/name/{name}")
    data class ByName(override val name: String) : BackgroundQuery, Query.ByName<BackgroundModel>
}
