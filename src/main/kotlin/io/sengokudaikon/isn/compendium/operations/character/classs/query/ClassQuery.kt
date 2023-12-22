package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ClassQuery : Query {
    @Resource("/api/class/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int, override val filters: String?) : ClassQuery, Query.All<List<ClassModel>>

    @Resource("/api/class/{id}")
    data class ById(override val id: String) : ClassQuery, Query.ById<ClassModel>

    @Resource("/api/class/name/{name}")
    data class ByName(override val name: String) : ClassQuery, Query.ByName<ClassModel>
}
