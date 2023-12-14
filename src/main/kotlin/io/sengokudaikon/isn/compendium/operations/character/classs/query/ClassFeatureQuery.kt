package io.sengokudaikon.isn.compendium.operations.character.classs.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query
import kotlinx.serialization.SerialName

interface ClassFeatureQuery : Query {
    @Resource("/api/class/{id}/feats/{page}/{size}")
    data class All(val id: String, override val page: Int, override val size: Int) :
        ClassFeatureQuery, Query.All<List<ClassFeatureModel>>

    @Resource("/api/class/{id}/feats/{feat_id}")
    data class ById(
        @SerialName("feat_id") override val id: String,
        @SerialName("id") val classId: String
    ) : ClassFeatureQuery, Query.ById<ClassFeatureModel>

    @Resource("/api/class/{id}/feats/{name}")
    data class ByName(val id: String, override val name: String) : ClassFeatureQuery, Query.ByName<ClassFeatureModel>
}
