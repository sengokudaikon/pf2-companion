package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ClassFeatureQuery : Query {
    @Resource("/api/class/{id}/feats")
    data class All(override val page: Int, override val size: Int) :
        ClassFeatureQuery, Query.All<List<ClassFeatureModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/class/{id}/feat/{featId}")
    data class ById(
        override val id: String
    ) : ClassFeatureQuery, Query.ById<ClassFeatureModel>

    @Resource("/api/class/{id}/feat")
    data class ByName(override val name: String) : ClassFeatureQuery, Query.ByName<ClassFeatureModel>
}
