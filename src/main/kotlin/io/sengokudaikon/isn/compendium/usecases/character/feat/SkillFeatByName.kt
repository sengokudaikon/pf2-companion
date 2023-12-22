package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.SkillFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.compendium.ports.character.ByNameSkillFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameSkillFeatPort::class])
class SkillFeatByName(override val repository: SkillFeatRepositoryPort) : GetByName<FeatQuery.Skill, FeatModel>(),
    ByNameSkillFeatPort {

        override suspend fun execute(query: FeatQuery.Skill): Result<FeatModel> {
            query as FeatQuery.Skill.ByName
            val skillName = query.skillName
            val filters: List<Filter>
            val skillProficiency = query.skillProficiency
            filters = if (skillProficiency.isNullOrEmpty()) {
                listOf(
                    Filter(FilterType.Skill, Comparison.CONTAINS, skillName)
                )
            } else {
                listOf(
                    Filter(FilterType.Skill, Comparison.CONTAINS, skillProficiency + "in" + skillName),
                )
            }
            val cacheKey = getCacheKey(query)
            return runCatching {
                withCache(cacheKey) {
                    repository.findByName(query.name, filters).getOrThrow()
                }
            }
        }
    override fun getCacheKey(query: FeatQuery.Skill): String {
        query as FeatQuery.Skill.ByName
        return "model_feat:name:${query.name}"
    }
}