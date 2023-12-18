package io.sengokudaikon.isn.compendium.operations.character.feat.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface FeatQuery : Query {
    interface Skill : FeatQuery {
        @Resource("/api/feats/skill/list/{page}/{size}")
        data class All(val page: Int, val size: Int) : Skill

        @Resource("/api/feats/skill/{id}")
        data class ById(val id: String) : Skill

        @Resource("/api/feats/skill/name/{name}")
        data class ByName(val name: String) : Skill
    }

    interface General : FeatQuery {
        @Resource("/api/feats/general/list/{page}/{size}")
        data class All(override val page: Int, override val size: Int) : General, Query.All<List<FeatModel>>

        @Resource("/api/feats/general/{id}")
        data class ById(override val id: String) : General, Query.ById<FeatModel>

        @Resource("/api/feats/general/name/{name}")
        data class ByName(override val name: String) : General, Query.ByName<FeatModel>
    }
}
