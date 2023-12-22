package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdSkillFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import io.sengokudaikon.isn.infrastructure.domain.Model
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SkillFeatIdHandler :
    ByIdHandler<FeatModel, FeatQuery.Skill.ById, ByIdSkillFeatPort>() {
    override val useCase: ByIdSkillFeatPort by inject()
    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
        val skillName = call.parameters["skillName"] ?: throw IllegalArgumentException("Missing skillName")
        val query = FeatQuery.Skill.ById(id, skillName, call.parameters["proficiency"])
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse<Model>() },
            ),
        )
    }
    override fun createQuery(id: String, secondaryId: String?): FeatQuery.Skill.ById {
        TODO()
    }
}