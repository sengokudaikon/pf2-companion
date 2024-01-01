package io.sengokudaikon.isn.compendium.usecases.character.feat

import com.mongodb.client.model.Filters
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.SkillFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameSkillFeatPort
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameSkillFeatPort::class])
class SkillFeatByName(override val repository: SkillFeatRepositoryPort) : GetByName<FeatQuery.Skill, FeatModel>(),
    ByNameSkillFeatPort {

        override suspend fun execute(query: FeatQuery.Skill): Result<FeatModel> {
            query as FeatQuery.Skill.ByName
            val skillName = query.skillName
            val skillProficiency = query.skillProficiency
            val criteria = Criteria()
            criteria.addCondition(
                if (skillProficiency.isNullOrEmpty()) {
                    Filters.elemMatch("system.prerequisites.value", Filters.regex("value", skillName))
                } else {
                    Filters.elemMatch("system.prerequisites.value", Filters.regex("value",skillProficiency + "in" + skillName))
                }
            )
            val cacheKey = getCacheKey(query)
            return runCatching {
                withCache(cacheKey) {
                    repository.findByName(query.name, criteria).getOrThrow()
                }
            }
        }
    override fun getCacheKey(query: FeatQuery.Skill): String {
        query as FeatQuery.Skill.ByName
        return "model_feat:name:${query.name}"
    }
}
