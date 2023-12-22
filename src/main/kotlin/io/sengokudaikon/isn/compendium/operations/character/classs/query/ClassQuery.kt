package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ClassQuery : Query {
    @Resource("/api/classes")
    data class All(override val page: Int, override val size: Int) : ClassQuery, Query.All<List<ClassModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/class/{id}")
    data class ById(override val id: String) : ClassQuery, Query.ById<ClassModel>

    @Resource("/api/class/")
    data class ByName(override val name: String) : ClassQuery, Query.ByName<ClassModel>
}
