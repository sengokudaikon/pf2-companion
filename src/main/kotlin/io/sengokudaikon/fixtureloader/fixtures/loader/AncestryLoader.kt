package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.fixtureloader.fixtures.model.AncestryFixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import java.io.File

@Single
class AncestryLoader(
    private val ancestryRepository: AncestryRepository,
) : FixtureLoader<AncestryFixture> {
    private val dataPath = "data/ancestries"
    override suspend fun insertIntoDatabase() {
        val ancestryFixtures = setUpFixtures()
        val existingAncestries = ancestryRepository.findAllNames().toSet()
        val newAncestryFixtures = ancestryFixtures.filter { it.name !in existingAncestries }

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
        val data = Json.decodeFromString<AncestryFixture>(string)

        return data
    }
}
