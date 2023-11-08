package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.fixtureloader.fixtures.model.ClassFeatureFixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class ClassFeatureLoader(
    private val classFeatRepository: ClassFeatureRepositoryPort,
) : FixtureLoader<ClassFeatureFixture> {
    private val dataPath = "data/classfeatures"
    override suspend fun insertIntoDatabase() {
        val classFixtures = setUpFixtures()
        val existingClasses = classFeatRepository.findAllNames().toSet()
        val newClassFixtures = classFixtures.filter { it.name !in existingClasses }
        newClassFixtures.forEach {
            classFeatRepository.create(it)
        }
    }

    private fun generateFromJson(string: String): ClassFeatureFixture {
        return runCatching {
            return@runCatching Json.decodeFromString<ClassFeatureFixture>(string)
        }.getOrElse {
            println("Error ${it.message} at $string")
            throw it
        }
    }

    override suspend fun setUpFixtures(): List<ClassFeatureFixture> {
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
        val fixtures = mutableListOf<ClassFeatureFixture>()
        for (file in files) {
            if (file.exists() && file.extension == "json") {
                val jsonString = file.readText()
                fixtures.add(generateFromJson(jsonString))
            }
        }

        return fixtures
    }
}
