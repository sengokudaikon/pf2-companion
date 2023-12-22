package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ClassFeatureQuery : Query {
    @Resource("/api/classfeats/{page}/{size}")
    data class All(override val page: Int, override val size: Int, override val filters: String?) :
        ClassFeatureQuery, Query.All<List<ClassFeatureModel>>

    @Resource("/api/classfeats/{id}")
    data class ById(
        override val id: String
    ) : ClassFeatureQuery, Query.ById<ClassFeatureModel>

    @Resource("/api/classfeats/{name}")
    data class ByName(override val name: String) : ClassFeatureQuery, Query.ByName<ClassFeatureModel>
}
