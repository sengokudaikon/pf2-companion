package io.sengokudaikon.isn.compendium.operations.character.feat.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface FeatQuery : Query {
    interface Skill : FeatQuery {
        @Resource("/api/feats/skill/list/{page}/{size}")
        data class All(val page: Int, val size: Int) : FeatQuery.Skill

        @Resource("/api/feats/skill/{id}")
        data class ById(val id: String) : FeatQuery.Skill

        @Resource("/api/feats/skill/name/{name}")
        data class ByName(val name: String) : FeatQuery.Skill
    }
    interface General : FeatQuery {
        @Resource("/api/feats/general/list/{page}/{size}")
        data class All(override val page: Int, override val size: Int) : FeatQuery.General, Query.All<List<FeatModel>>

        @Resource("/api/feats/general/{id}")
        data class ById(override val id: String) : FeatQuery.General, Query.ById<FeatModel>

        @Resource("/api/feats/general/name/{name}")
        data class ByName(override val name: String) : FeatQuery.General, Query.ByName<FeatModel>
    }
}
