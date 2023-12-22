package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameSkillFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SkillFeatNameHandler :
    ByNameHandler<FeatModel, FeatQuery.Skill.ByName, ByNameSkillFeatPort>() {
    override val useCase: ByNameSkillFeatPort by inject()

    override suspend fun handle(call: ApplicationCall) {
        val name = call.parameters["name"] ?: throw IllegalArgumentException("Missing name")
        val skillName = call.parameters["skillName"] ?: throw IllegalArgumentException("Missing skillName")
        val proficiency = call.parameters["proficiency"]
        val query = FeatQuery.Skill.ByName(name, skillName, proficiency)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse<FeatModel>() },
            ),
        )

    }

    override fun createQuery(name: String, id: String?): FeatQuery.Skill.ByName {
        TODO("Not yet implemented")
    }
}