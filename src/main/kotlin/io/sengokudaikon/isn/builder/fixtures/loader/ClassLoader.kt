package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.ClassFixture
import io.sengokudaikon.isn.compendium.domain.character.classs.repository.ClassRepositoryPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File

class ClassLoader(
    private val classRepository: ClassRepositoryPort,
) : FixtureLoader<ClassFixture> {
    private val dataPath = "data/classes"
    override suspend fun insertIntoDatabase() {
        val classFixtures = setUpFixtures()
        val existingClasses = classRepository.findAllNames().toSet()
        val newClassFixtures = classFixtures.filter { it.name !in existingClasses }
        newClassFixtures.forEach {
            classRepository.create(it)
        }
    }

    private fun generateFromJson(string: String): ClassFixture {
        return runCatching {
            return@runCatching Json.decodeFromString<ClassFixture>(string)
        }.getOrElse {
            println("Error ${it.message} at $string")
            throw it
        }
    }

    override suspend fun setUpFixtures(): List<ClassFixture> {
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
        val fixtures = mutableListOf<ClassFixture>()
        for (file in files) {
            if (file.exists() && file.extension == "json") {
                val jsonString = file.readText()
                fixtures.add(generateFromJson(jsonString))
            }
        }

        return fixtures
    }
}
