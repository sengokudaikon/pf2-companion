package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.BackgroundFixture
import io.sengokudaikon.isn.compendium.persistence.character.background.repository.BackgroundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class BackgroundLoader(
    private val backgroundRepository: BackgroundRepository,
) : FixtureLoader<BackgroundFixture> {
    private val dataPath = "data/backgrounds"

    override suspend fun insertIntoDatabase() {
        val backgroundFixtures = setUpFixtures()
        val existingBackgrounds = backgroundRepository.findAllNames().toSet()
        val newBackgroundFixtures = backgroundFixtures.filter { it.name !in existingBackgrounds }

        newBackgroundFixtures.forEach {
            backgroundRepository.create(it)
        }
    }

    private fun generateFromJson(string: String): BackgroundFixture {
        return runCatching {
            return@runCatching Json.decodeFromString<BackgroundFixture>(string)
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
}
