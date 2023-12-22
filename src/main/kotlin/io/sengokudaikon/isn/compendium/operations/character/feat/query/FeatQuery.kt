package io.sengokudaikon.isn.compendium.operations.character.feat.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface FeatQuery : Query {
    interface Skill : FeatQuery {
        @Resource("/api/feats/skills")
        data class All(override val page: Int, override val size: Int) : Skill, Query.All<List<FeatModel>> {
            override var filters: String? = null
            override var sort: String? = null
            var skillName: String? = null
            var skillProficiency: String? = null
        }

        @Resource("/api/feats/skill/{id}")
        data class ById(override val id: String, val skillName: String, val skillProficiency: String?) : Skill, Query.ById<FeatModel>

        @Resource("/api/feats/skill")
        data class ByName(override val name: String, val skillName: String, val skillProficiency: String?) : Skill, Query.ByName<FeatModel>
    }

    interface General : FeatQuery {
        @Resource("/api/feats/generals")
        data class All(override val page: Int, override val size: Int) : General, Query.All<List<FeatModel>> {
            override var filters: String? = null
            override var sort: String? = null
        }

        @Resource("/api/feats/general/{id}")
        data class ById(override val id: String) : General, Query.ById<FeatModel>

        @Resource("/api/feats/general")
        data class ByName(override val name: String) : General, Query.ByName<FeatModel>
    }
}
