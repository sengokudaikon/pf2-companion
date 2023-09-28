package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.domain.world.model.TraitType
import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.Choice
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import io.sengokudaikon.dbfinder.persistence.character.background.repository.BackgroundRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.RuleRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.TraitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

class BackgroundFixtureLoader(
    private val backgroundRepository: BackgroundRepository,
    private val traitRepository: TraitRepository,
    private val rulesRepository: RuleRepository,
) : FixtureLoader<BackgroundFixture> {
    private val dataPath = "data/backgrounds"

    override suspend fun insertIntoDatabase() {
        val backgroundFixtures = setUpFixtures()
        val existingBackgrounds = backgroundRepository.findAllNames().toSet()
        val newBackgroundFixtures = backgroundFixtures.filter { it.name !in existingBackgrounds }
        val dbTraits = traitRepository.findAllNames().toList()
        newBackgroundFixtures.flatMap {
            it.traits
        }.filter { it.name !in dbTraits }.apply {
            traitRepository.batchInsertModels(this.toSet())
        }

        val dbRules = rulesRepository.findAllNames().toList()
        newBackgroundFixtures.flatMap {
            it.rules
        }.filter { it.key !in dbRules }.apply {
            rulesRepository.batchInsert(this.map { it.toModel() }.toSet())
        }

        newBackgroundFixtures.forEach {
            backgroundRepository.create(it)
        }
    }

    private fun generateFromJson(string: String): BackgroundFixture {
        return runCatching {
            val data = Json.decodeFromString<BackgroundJson>(string)
            return@runCatching data.toFixture()
        }.getOrElse {
            println("Error ${it.message} at $string")
            throw it
        }
    }

    override suspend fun setUpFixtures(): MutableList<BackgroundFixture> {
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
        val fixtures = mutableListOf<BackgroundFixture>()
        for (file in files) {
            if (file.exists() && file.extension == "json") {
                val jsonString = file.readText()
                fixtures.add(generateFromJson(jsonString))
            }
        }

        return fixtures
    }

    @Serializable
    data class BackgroundJson(
        val img: String,
        val name: String,
        val boosts: List<String>,
        val description: String,
        val items: List<JsonItem>,
        val rules: List<JsonRule>,
        val source: String,
        val traits: List<JsonTrait>,
        val rarity: String,
        val trainedLore: String,
        val trainedSkills: List<String>,
    ) {
        fun toFixture(): BackgroundFixture {
            val fixedAbilities = mapOf(
                "str" to Ability.Strength,
                "dex" to Ability.Dexterity,
                "con" to Ability.Constitution,
                "int" to Ability.Intelligence,
                "wis" to Ability.Wisdom,
                "cha" to Ability.Charisma,
                "anything" to Ability.Anything,
            )
            val fixedRarity = enumValueOf<Rarity>(rarity.uppercase(Locale.getDefault()))

            val boosts = boosts.map { boost ->
                if (boosts.isEmpty()) {
                    return@map Choice.fromList<Ability>(
                        listOf(
                            Ability.Anything,
                        ),
                    )
                } else if (boosts.contains(" or ")) {
                    val split = boost.split(" or ")
                    val abilities = split.map { fixedAbilities[it] ?: Ability.Anything }
                    return@map Choice.fromList<Ability>(abilities)
                } else if (boost == "any") {
                    return@map Choice.fromList<Ability>(
                        listOf(
                            Ability.Anything,
                        ),
                    )
                }
                val ability = Choice.fromList<Ability>(listOf(fixedAbilities[boost] ?: Ability.Anything))
                ability
            }

            val fixedTraits = traits.map { Trait(it.name, it.description, it.source, TraitType.valueOf(it.type)) }
            val skills = trainedSkills.map {
                if (it.contains(" or ")) {
                    val skills = it.split(" or ").map { Skills.valueOf(it.uppercase()) }
                    return@map Choice.fromList<Skills>(skills)
                } else if (it.contains("|")) {
                    val skills = it.split("|").map { Skills.valueOf(it.uppercase()) }
                    return@map Choice.fromList<Skills>(skills)
                }
                Choice.fromList(listOf(Skills.valueOf(it.uppercase())))
            }
            return BackgroundFixture(
                img = img,
                name = name,
                rarity = fixedRarity,
                boosts = boosts,
                description = description,
                rules = rules,
                source = source,
                traits = fixedTraits,
                items = items,
                trainedLore = trainedLore,
                trainedSkills = skills,
            )
        }
    }
}
