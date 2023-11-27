package io.sengokudaikon.isn.builder.fixtures.loader.web

import io.sengokudaikon.isn.builder.fixtures.loader.FixtureLoader
import io.sengokudaikon.isn.builder.fixtures.model.TraitFixture
import io.sengokudaikon.isn.compendium.persistence.cache.InMemoryCache
import io.sengokudaikon.isn.compendium.persistence.world.repository.TraitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.io.File

class TraitFixtureLoader(
    private val traitRepository: TraitRepository,
) : FixtureLoader<TraitFixture> {
    override suspend fun insertIntoDatabase() {
        val traitFixtures = setUpFixtures()
        val existingTraits = traitRepository.findAllNames().toSet()
        val newTraitFixtures = traitFixtures.filter { it.name !in existingTraits }
        runCatching {
            traitRepository.batchInsert(newTraitFixtures.toSet())
        }.getOrElse {
            println("Error inserting traits into database, error: ${it.message}")
        }
    }

    override suspend fun setUpFixtures(): List<TraitFixture> {
        val cacheKey = "traits:fixtures"
        val listCache = InMemoryCache<List<TraitFixture>> {
            val result = mutableListOf<TraitFixture>()
            val traitTypes: MutableMap<String, List<String>> = Json.decodeFromString(
                MapSerializer(String.serializer(), ListSerializer(String.serializer())),
                File("src/main/resources/data/traits/trait_list.json").readText(),
            ).toMutableMap()

            for (id in 1..514) {
                try {
                    val doc = Jsoup.connect("https://2e.aonprd.com/Traits.aspx?ID=$id").get()

                    val name = doc.select("h1.title a").first()?.text()
                    val sourceElement = doc.select("a[href*=Sources.aspx] i").first()
                    val source = sourceElement?.text()
                    val parent = sourceElement?.parent()

                    if (name != null && source != null && parent != null) {
                        val nextSibling = parent.nextSibling()
                        val description = if (nextSibling != null) generateDescription(nextSibling, parent) else ""

                        var type = "General"
                        for (entry in traitTypes) {
                            if (entry.value.contains(name)) {
                                type = entry.key
                                traitTypes[entry.key] = entry.value - name
                                break
                            }
                        }
                        val data = TraitFixture(name, description, source, type)

                        result.add(data)
                    }
                } catch (e: Exception) {
                    println("Error fetching trait with id $id, error: ${e.message}")
                }
            }
            result
        }
        return withContext(Dispatchers.IO) {
            listCache.runner.get(cacheKey).get()
        }
    }

    private fun generateDescription(startNode: Node?, sourceParent: Node): String {
        val descriptionBuilder = StringBuilder()
        var currNode = startNode

        while (currNode != null) {
            when (currNode) {
                is Element -> if (currNode != sourceParent && currNode.tagName() != "h2") {
                    descriptionBuilder.append(currNode.ownText())
                }

                is TextNode -> descriptionBuilder.append(currNode.text())
            }

            if (currNode is Element && (currNode.tagName() == "h2" || currNode == sourceParent)) {
                break
            }
            currNode = currNode.nextSibling()
        }

        return descriptionBuilder.toString()
    }
}
