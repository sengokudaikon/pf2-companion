package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.operations.character.background.response.BackgroundResponse
import io.sengokudaikon.isn.infrastructure.operations.Response

class BackgroundMapper : Mapper<BackgroundModel> {
    override fun toResponse(model: BackgroundModel): Response<BackgroundModel> {
        return with(model) {
            BackgroundResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                feats = feats.mapValues { it.value.toResponse() },
                actions = actions.mapValues { it.value.toResponse() },
                description = system.description.value,
                rules = system.rules.map { it.toResponse() },
                publication = system.publication,
                traits = system.traits.toResponse(),
                boosts = system.boosts.toAbilityList(),
                items = system.items.mapValues { it.value.toResponse() },
                trainedLore = system.trainedLore,
                trainedSkills = system.trainedSkills.toSkillList(),
            )
        }
    }

    fun Map<String, AncestryModel.SystemProperty.AbilityScore>.toAbilityList(): List<Ability> {
        return this.map {
            it.value.toAbility()
        }
    }
}
