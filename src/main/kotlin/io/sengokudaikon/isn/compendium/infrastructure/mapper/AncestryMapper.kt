package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.operations.character.ancestry.response.AncestryResponse
import org.koin.core.annotation.Single

@Single(binds = [Mapper::class])
class AncestryMapper : Mapper<AncestryModel> {
    override fun toResponse(model: AncestryModel): AncestryResponse {
        return with(model) {
            AncestryResponse(
                id = id.toHexString(),
                name = name,
                type = type,
                description = system.description.value,
                rules = system.rules.map { it.toResponse() },
                traits = system.traits.toResponse(),
                publication = system.publication,
                additionalLanguages = system.additionalLanguages,
                boosts = system.boosts.toAbilityList(),
                flaws = system.flaws.toAbilityList(),
                hp = system.hp,
                languages = system.languages,
                reach = system.reach,
                size = system.size,
                source = system.source,
                speed = system.speed,
                items = system.items.mapValues { it.value.toResponse() },
                vision = system.vision,
                additionalSense = system.additionalSense,
                ancestryFeatures = ancestryFeatures.mapValues { it.value.toResponse() },
            )
        }
    }

    fun Map<String, AncestryModel.SystemProperty.AbilityScore>.toAbilityList(): List<Ability> {
        return this.map {
            it.value.toAbility()
        }
    }
}
