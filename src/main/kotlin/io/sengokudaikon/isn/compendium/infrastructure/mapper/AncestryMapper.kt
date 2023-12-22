package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.infrastructure.operations.response.AncestryResponse
import org.koin.core.annotation.Single

@Single
class AncestryMapper(
    private val ancestryFeatureMapper: AncestryFeatureMapper,
    private val heritageMapper: HeritageMapper,
) : Mapper<AncestryModel> {
    override fun toResponse(model: AncestryModel): AncestryResponse {
        return with(model) {
            AncestryResponse(
                id = id.toHexString(),
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                traits = system.traits.value,
                publication = system.publication,
                additionalLanguages = system.additionalLanguages,
                boosts = system.boosts.toAbilityList().joinToString("/"),
                flaws = system.flaws.toAbilityList().joinToString("/"),
                hp = system.hp,
                languages = system.languages,
                reach = system.reach,
                size = system.size,
                source = system.source,
                speed = system.speed,
                items = system.items.mapValues { it.value.toResponse() },
                vision = system.vision,
                additionalSense = system.additionalSense,
                ancestryFeatures = ancestryFeatures.mapValues { ancestryFeatureMapper.toResponse(it.value) },
                heritages = heritages.mapValues { heritageMapper.toResponse(it.value) },
            )
        }
    }
}
