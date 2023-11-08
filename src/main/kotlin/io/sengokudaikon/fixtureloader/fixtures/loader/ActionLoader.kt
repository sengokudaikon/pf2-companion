package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.ActionRepository
import io.sengokudaikon.fixtureloader.fixtures.model.ActionFixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class ActionLoader(
    private val actionRepository: ActionRepository,
) : FixtureLoader<ActionFixture> {
    private val dataPath = "data/actions"
    override suspend fun insertIntoDatabase() {
        val actionFixtures = setUpFixtures()
        val existingActions = actionRepository.findAllNames().toSet()
        val newBackgroundFixtures = actionFixtures.filter { it.name !in existingActions }
        newBackgroundFixtures.forEach {
            actionRepository.create(it)
        }
    }

    override suspend fun setUpFixtures(): List<ActionFixture> {
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
        val fixtures = mutableListOf<ActionFixture>()
        for (file in files) {
            if (file.exists() && file.extension == "json") {
                val jsonString = file.readText()
                fixtures.add(generateFromJson(jsonString))
            }
        }

        return fixtures
    }

    private fun generateFromJson(string: String): ActionFixture {
        return runCatching {
            return@runCatching Json.decodeFromString<ActionFixture>(string)
        }.getOrElse {
            println("Error ${it.message} at $string")
            throw it
        }
    }
}
