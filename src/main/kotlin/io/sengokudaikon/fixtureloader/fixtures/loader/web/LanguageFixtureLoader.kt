package io.sengokudaikon.fixtureloader.fixtures.loader.web

import io.sengokudaikon.dbfinder.domain.world.global.model.Language
import io.sengokudaikon.dbfinder.domain.world.global.repository.LanguageRepositoryPort
import io.sengokudaikon.dbfinder.persistence.cache.InMemoryCache
import io.sengokudaikon.fixtureloader.fixtures.loader.FixtureLoader
import io.sengokudaikon.fixtureloader.fixtures.model.LanguageFixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

class LanguageFixtureLoader(
    private val languageRepository: LanguageRepositoryPort,
) : FixtureLoader<LanguageFixture> {
    override suspend fun insertIntoDatabase() {
        val languageFixtures = setUpFixtures()
        val existingLanguages = languageRepository.findAllNames().toSet()
        val newTraitFixtures = languageFixtures.filter { it.name !in existingLanguages }
        runCatching {
            languageRepository.batchInsert(
                newTraitFixtures.map { Language(it.name, it.description, it.contentSrc) }
                    .toSet(),
            )
        }.getOrElse {
            println("Error inserting languages into database, error: ${it.message}")
        }
    }

    override suspend fun setUpFixtures(): List<LanguageFixture> {
        val cacheKey = "languages:fixtures"
        val listCache = InMemoryCache<List<LanguageFixture>> {
            val result = mutableListOf<LanguageFixture>()

            for (id in 1..121) {
                try {
                    val doc = Jsoup.connect("https://2e.aonprd.com/Languages.aspx?ID=$id").get()

                    val name =
                        doc.select("span#ctl00_RadDrawer1_Content_MainContent_DetailedOutput h1.title").first()?.text()
                    val sourceElement = doc.select("a[href*=Sources.aspx] i").first()
                    val source = sourceElement?.text()
                    val parent = sourceElement?.parent()

                    if (name != null && source != null && parent != null) {
                        val nextSibling = parent.nextSibling()
                        val description = if (nextSibling != null) generateDescription(nextSibling, parent) else ""

                        val data = LanguageFixture(name, description, source)

                        result.add(data)
                    }
                } catch (e: Exception) {
                    println("Error fetching language with id $id, error: ${e.message}")
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
