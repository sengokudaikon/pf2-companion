package io.sengokudaikon.isn.compendium.usecases.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.SkillFeatRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.FilterType
import io.sengokudaikon.isn.compendium.ports.character.ListSkillFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListSkillFeatPort::class])
class SkillFeatList(override val repository: SkillFeatRepositoryPort) :
    GetList<FeatQuery.Skill, FeatModel>(), ListSkillFeatPort {
        override suspend fun execute(query: FeatQuery.Skill): Result<List<FeatModel>> {
            query as FeatQuery.Skill.All
            val skillName = query.skillName
            val filters: List<Filter>
            val skillProficiency = query.skillProficiency
            filters = if (skillName.isNullOrEmpty()) {
                emptyList()
            } else if (skillProficiency.isNullOrEmpty()) {
                listOf(
                    Filter(FilterType.Skill, Comparison.CONTAINS, skillName)
                )
            } else {
                listOf(
                    Filter(FilterType.Skill, Comparison.CONTAINS, "$skillProficiency in $skillName"),
                )
            }
            val cacheKey = getCacheKey(query)
            return runCatching {
                withCache(cacheKey) {
                    repository.findAll(query.page, query.size, filters).getOrThrow()
                }
            }
        }
    override fun getCacheKey(query: FeatQuery.Skill): String {
        query as FeatQuery.Skill.All
        return "model_feat:all:${query.page}:${query.size}"
    }
}