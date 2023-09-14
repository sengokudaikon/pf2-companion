package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AdditionalLanguages
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense
import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.VisionSenses
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.RuleMode
import io.sengokudaikon.dbfinder.persistence.enums.toSizeEnum
import io.sengokudaikon.dbfinder.persistence.enums.toVisionEnum
import io.sengokudaikon.dbfinder.persistence.world.entity.Languages
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import java.io.File
import java.util.*

@Single
class AncestryFixtureLoader(
    private val ancestryRepository: AncestryRepository,
) {
    private val dataPath = "data/ancestries"
    suspend fun insertIntoDatabase() {
        val ancestryFixtures = setUpFixtures()
        val existingAncestries = ancestryRepository.findAllNames().toSet()
        val newAncestryFixtures = ancestryFixtures.filter { it.name !in existingAncestries }
        val dbVisionSenses = suspendedTransactionAsync {
            io.sengokudaikon.dbfinder.domain.character.ancestry.entity.VisionSense.all().map { it.name }
        }.await()
        newAncestryFixtures.flatMap {
            listOfNotNull(it.vision, it.additionalSense)
        }.filter { it.name !in dbVisionSenses }.apply {
            insertMultipleVisionSenses(this.toSet())
        }
        val dbLanguages = suspendedTransactionAsync {
            io.sengokudaikon.dbfinder.domain.world.entity.Language.all().map { it.name }
        }.await()
        newAncestryFixtures.flatMap {
            it.languages.plus(it.additionalLanguages.values)
        }.filter { it.name !in dbLanguages }.apply {
            insertMultipleLanguages(this.toSet())
        }
        val dbTraits = suspendedTransactionAsync {
            io.sengokudaikon.dbfinder.domain.world.entity.Trait.all().map { it.name }
        }.await()
        newAncestryFixtures.flatMap {
            it.traits
        }.filter { it.name !in dbTraits }.apply {
            insertMultipleTraits(this.toSet())
        }
        val dbRules = suspendedTransactionAsync {
            io.sengokudaikon.dbfinder.domain.world.entity.Rule.all().map { it.name }
        }.await()
        newAncestryFixtures.flatMap {
            it.rules
        }.filter { it.key !in dbRules }.apply {
            insertMultipleRules(this.toSet())
        }

        newAncestryFixtures.forEach { ancestry ->
            ancestryRepository.create(AncestryCommand.Create(ancestry))
        }
    }

    private suspend fun insertMultipleVisionSenses(visionSenses: Set<VisionSense>) {
        suspendedTransactionAsync {
            VisionSenses.batchInsert(visionSenses) { visionSense ->
                this[VisionSenses.name] = visionSense.name
                this[VisionSenses.visionRange] = visionSense.range
                this[VisionSenses.precision] =
                    if (visionSense.isPrecise) VisionSenses.Precision.PRECISE else VisionSenses.Precision.IMPRECISE
            }
        }.await()
    }

    private suspend fun insertMultipleLanguages(languages: Set<Language>) {
        suspendedTransactionAsync {
            Languages.batchInsert(languages) { language ->
                this[Languages.name] = language.name
                this[Languages.description] = language.description
            }
        }.await()
    }

    private suspend fun insertMultipleTraits(trait: Set<Trait>) {
        suspendedTransactionAsync {
            Traits.batchInsert(trait) { trait ->
                this[Traits.name] = trait.name
                this[Traits.description] = trait.description
                this[Traits.contentSrc] = trait.contentSrc
            }
        }.await()
    }

    private suspend fun insertMultipleRules(rules: Set<JsonRules>) {
        suspendedTransactionAsync {
            Rules.batchInsert(rules) {
                this[Rules.name] = it.key
                this[Rules.mode] = RuleMode.valueOf(it.mode.uppercase())
                this[Rules.priority] = it.priority ?: 0
                this[Rules.prompt] = it.prompt
                this[Rules.path] = it.path
            }
        }.await()
    }

    private suspend fun setUpFixtures(): List<AncestryFixture> {
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

    private fun generateFromJson(string: String): AncestryFixture {
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
        val rules: List<JsonRules>,
        val size: String,
        val source: String,
        val speed: Int,
        val traits: List<String>,
        val vision: String,
        val rarity: String,
        val type: String,
    ) {
        fun toFixture(): AncestryFixture {
            val fixedAbilities = mapOf(
                "str" to Ability.Strength,
                "dex" to Ability.Dexterity,
                "con" to Ability.Constitution,
                "int" to Ability.Intelligence,
                "wis" to Ability.Wisdom,
                "cha" to Ability.Charisma,
                "Anything" to Ability.Anything,
            )
            val fixedRarity = enumValueOf<Rarity>(rarity.uppercase(Locale.getDefault()))
            val fixedAdditionalLanguages = AdditionalLanguages(
                additionalLanguages.map { Language(it) },
            )

            val boosts = boosts.map { boost ->
                if (boosts.isEmpty()) {
                    return@map Ability.Anything
                }
                val ability = fixedAbilities[boost] ?: Ability.Anything
                ability
            }

            val flaws = flaws.map { flaw ->
                if (flaws.isEmpty()) {
                    return@map Ability.None
                }
                val ability = fixedAbilities[flaw] ?: Ability.None
                ability
            }
            val fixedSize = size.toSizeEnum()
            val fixedVisionSense = VisionSense(vision.toVisionEnum(), 0, false)
            val fixedLanguages = languages.map { Language(it) }
            val fixedTraits = traits.map { Trait(it, "", false, "") }
            val physicalFeatures = items.map {
                AncestryPhysicalFeature(
                    name = it.name,
                    description = "",
                    img = it.img,
                    level = it.level,
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
                rules = rules,
                size = fixedSize,
                source = source,
                speed = speed,
                traits = fixedTraits,
                vision = fixedVisionSense,
                additionalSense = null,
                physicalFeatures = physicalFeatures,
            )
        }
    }

    @Serializable
    data class JsonRules(
        val key: String,
        val mode: String,
        val priority: Int? = null,
        val prompt: String? = null,
        val value: Map<String, String>,
        val path: String? = null,
    )

    @Serializable
    data class JsonItem(
        val img: String,
        val level: Int,
        val name: String,
        val uuid: String,
    )
}
