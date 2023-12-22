package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ListSkillFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SkillFeatListHandler :
    ListHandler<FeatModel, FeatQuery.Skill.All, ListSkillFeatPort>() {
    override val useCase: ListSkillFeatPort by inject()

    override suspend fun handle(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 10
        val filters = call.parameters["filters"]
        val sort = call.parameters["sort"]
        val skillName = call.parameters["skillName"]
        val proficiency = call.parameters["skillProficiency"]
        val query =  FeatQuery.Skill.All(page, size).apply {
            this.skillName = skillName
            skillProficiency = proficiency
            this.sort = sort
            this.filters = filters
        }
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.map { it.toResponse<FeatModel>() } },
            ),
        )

    }
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): FeatQuery.Skill.All {
        TODO()
    }
}