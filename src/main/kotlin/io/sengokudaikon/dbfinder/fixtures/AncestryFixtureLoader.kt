package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AdditionalLanguages
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense
import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Rule
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.domain.world.model.TraitType
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.toSizeEnum
import io.sengokudaikon.dbfinder.infrastructure.enums.toVisionEnum
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.VisionSenseRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.LanguageRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.RuleRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.TraitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import java.io.File
import java.util.*

@Single
class AncestryFixtureLoader(
    private val ancestryRepository: AncestryRepository,
    private val traitRepository: TraitRepository,
    private val visionSenseRepository: VisionSenseRepository,
    private val languageRepository: LanguageRepository,
    private val rulesRepository: RuleRepository,
) : FixtureLoader<AncestryFixture> {
    private val dataPath = "data/ancestries"
    override suspend fun insertIntoDatabase() {
        val ancestryFixtures = setUpFixtures()
        val existingAncestries = ancestryRepository.findAllNames().toSet()
        val newAncestryFixtures = ancestryFixtures.filter { it.name !in existingAncestries }
        val dbVisionSenses = visionSenseRepository.findAllNames().toSet()
        newAncestryFixtures.flatMap {
            listOfNotNull(it.vision, it.additionalSense)
        }.filter { it.name.name in dbVisionSenses }.apply {
            visionSenseRepository.batchInsert(this.toSet())
        }
        val dbLanguages = languageRepository.findAllNames().toSet()
        newAncestryFixtures.flatMap {
            it.languages.plus(it.additionalLanguages.values)
        }.filter { it.name !in dbLanguages }.apply {
            languageRepository.batchInsert(this.toSet())
        }
        val dbTraits = traitRepository.findAllNames()
        newAncestryFixtures.flatMap {
            it.traits
        }.filter { it.name !in dbTraits }.apply {
            traitRepository.batchInsertModels(this.toSet())
        }
        val dbRules = rulesRepository.findAllNames().toSet()
        newAncestryFixtures.flatMap {
            it.rules
        }.filter { it.name !in dbRules }.apply {
            rulesRepository.batchInsert(this.toSet())
        }

        newAncestryFixtures.forEach { ancestry ->
            ancestryRepository.create(AncestryCommand.Create(ancestry))
        }
    }

    override suspend fun setUpFixtures(): List<AncestryFixture> {
        val urls = withContext(Dispatchers.IO) {
            javaClass.classLoader.getResources(dataPath)
        }
        val files = mutableListOf<File>()

        while (urls.hasMoreElements()) {
            val url = urls.nextElement()
            val file = File(url.toURI())
            if (file.isDirectory) {
                files.addAll(file.listFiles().orEmpty())
            } else {
                files.add(file)
            }
        }
        val fixtures = mutableListOf<AncestryFixture>()
        for (file in files) {
            if (file.exists() && file.extension == "json") {
                val jsonString = file.readText()
                fixtures.add(generateFromJson(jsonString))
            }
        }

        return fixtures
    }

    fun generateFromJson(string: String): AncestryFixture {
        val data = Json.decodeFromString<AncestryJson>(string)

        return data.toFixture()
    }

    @Serializable
    data class AncestryJson(
        val img: String,
        val name: String,
        val additionalLanguages: List<String>,
        val boosts: List<String>,
        val description: String,
        val flaws: List<String>,
        val hp: Int,
        val items: List<JsonItem>,
        val languages: List<String>,
        val reach: Int,
        val rules: List<JsonRule>,
        val size: String,
        val source: String,
        val speed: Int,
        val traits: List<JsonTrait>,
        val vision: String,
        val rarity: String,
        val type: String,
    ) {
        fun toFixture(): AncestryFixture {
            val fixedAbilities = mapOf(
                "str" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Strength,
                "dex" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Dexterity,
                "con" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Constitution,
                "int" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Intelligence,
                "wis" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Wisdom,
                "cha" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Charisma,
                "Anything" to io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Anything,
            )
            val fixedRarity =
                enumValueOf<Rarity>(rarity.uppercase(Locale.getDefault()))
            val fixedAdditionalLanguages = AdditionalLanguages(
                additionalLanguages.map { Language(it) },
            )

            val boosts = boosts.map { boost ->
                if (boosts.isEmpty()) {
                    return@map io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Anything
                }
                val ability = fixedAbilities[boost] ?: io.sengokudaikon.dbfinder.infrastructure.enums.Ability.Anything
                ability
            }

            val flaws = flaws.map { flaw ->
                if (flaws.isEmpty()) {
                    return@map io.sengokudaikon.dbfinder.infrastructure.enums.Ability.None
                }
                val ability = fixedAbilities[flaw] ?: io.sengokudaikon.dbfinder.infrastructure.enums.Ability.None
                ability
            }
            val fixedSize = size.toSizeEnum()
            val fixedVisionSense = VisionSense(vision.toVisionEnum(), 0, false)
            val fixedLanguages = languages.map { Language(it) }
            val physicalFeatures = items.map {
                AncestryPhysicalFeature(
                    name = it.name ?: it.uuid.replace("Feat.", ""),
                    description = "",
                    img = it.img ?: "icons/default-icons/feat.svg",
                    level = it.level,
                )
            }
            val fixedRules = rules.map {
                Rule(
                    name = it.key,
                    description = it.description,
                    mode = io.sengokudaikon.dbfinder.infrastructure.enums.RuleMode.valueOf(it.mode),
                    isArchived = false,
                    contentSrc = source,
                    priority = it.priority ?: 0,
                    prompt = it.prompt ?: "",
                    ruleChoices = it.values.map { (key, value) ->
                        io.sengokudaikon.dbfinder.domain.world.model.RuleChoice(
                            name = key,
                            value = value,
                        )
                    },
                )
            }

            return AncestryFixture(
                img = img,
                name = name,
                rarity = fixedRarity,
                additionalLanguages = fixedAdditionalLanguages,
                boosts = boosts,
                description = description,
                flaws = flaws,
                hp = hp,
                languages = fixedLanguages,
                reach = reach,
                rules = fixedRules,
                size = fixedSize,
                source = source,
                speed = speed,
                traits = traits.map { Trait(it.name, it.description, it.source, TraitType.valueOf(it.type)) },
                vision = fixedVisionSense,
                additionalSense = null,
                physicalFeatures = physicalFeatures,
            )
        }
    }
}
