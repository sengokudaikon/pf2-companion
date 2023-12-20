package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.infrastructure.operations.response.BackgroundResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single
class BackgroundMapper(
    private val featMapper: FeatMapper,
    private val actionMapper: ActionMapper,
) : Mapper<BackgroundModel> {
    override fun toResponse(model: BackgroundModel): Response<BackgroundModel> {
        return with(model) {
            BackgroundResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                feats = feats.mapValues { featMapper.toResponse(it.value) },
                actions = actions.mapValues { actionMapper.toResponse(it.value) },
                description = system.description.value,
                rules = system.rules?.asDocument()?.toJson(),
                publication = system.publication,
                traits = system.traits.toResponse(),
                boosts = system.boosts.toAbilityList(),
                trainedLore = system.trainedLore,
                trainedSkills = system.trainedSkills.value.map {
                    Skills.from(it)
                },
            )
        }
    }

    fun Map<String, AncestryModel.SystemProperty.AbilityScore>.toAbilityList(): List<Ability> {
        return this.map {
            it.value.toAbilityList()
        }.flatten()
    }
}
