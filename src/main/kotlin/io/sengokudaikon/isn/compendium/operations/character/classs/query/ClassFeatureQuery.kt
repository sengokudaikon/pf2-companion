package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query
import kotlinx.serialization.SerialName

interface ClassFeatureQuery : Query {

    interface ByClass : ClassFeatureQuery {
        @Resource("/api/class/{id}/feats/{page}/{size}")
        data class All(val id: String, override val page: Int, override val size: Int) :
            ByClass, Query.All<List<ClassFeatureModel>>

        @Resource("/api/class/{id}/feats/{feat_id}")
        data class ById(
            @SerialName("feat_id") override val id: String,
            @SerialName("id") val classId: String
        ) : ByClass, Query.ById<ClassFeatureModel>

        @Resource("/api/class/{id}/feats/{name}")
        data class ByName(val id: String, override val name: String) : ByClass, Query.ByName<ClassFeatureModel>
    }

    @Resource("/api/classfeats/{page}/{size}")
    data class All(override val page: Int, override val size: Int) :
        ClassFeatureQuery, Query.All<List<ClassFeatureModel>>

    @Resource("/api/classfeats/{id}")
    data class ById(
        override val id: String
    ) : ClassFeatureQuery, Query.ById<ClassFeatureModel>

    @Resource("/api/classfeats/{name}")
    data class ByName(override val name: String) : ClassFeatureQuery, Query.ByName<ClassFeatureModel>
}
