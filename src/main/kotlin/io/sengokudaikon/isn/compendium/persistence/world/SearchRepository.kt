package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.client.model.Filters.and
import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.exceptionLogger
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromJsonElement
import kotlinx.serialization.json.JsonObject
import org.bson.Document
import org.koin.core.annotation.Single

@Single
class SearchRepository {
    val db = DatabaseFactory.database

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun searchAnything(query: SearchQuery, criteria: Criteria): List<SearchResult> {
        val collections = db.listCollectionNames().toList()
        val searchResults = mutableListOf<SearchResult>()
        val sortDocument = criteria.getSort()
        collections.asFlow().buffer(1000).flatMapMerge { collection ->
            val col = db.getCollection<Document>(collection)
            val bsonFilters = criteria.build()
            val matchStage = Document("\$match", and(bsonFilters))
            val pipeline = mutableListOf<Document>().apply {
                matchStage.let { add(it) }
                add(
                    Document.parse(
                        """
                {
                    ${'$'}search: {
                        index: "default",
                        text: {
                            query: "${query.query}",
                            path: {
                                wildcard: "*"
                            },
                        }
                    }
                }
                """
                    )
                )
                add(
                    Document.parse(
                        """
                {
                    ${"$"}project: {
                        "score": { ${"$"}meta: "searchScore" },
                        "_id": 1,
                        "img": 1,
                        "name": 1,
                        "type": 1,
                        "system": 1,
                    }
                }
                """
                    )
                )
                add(Document("\$sort", sortDocument))
            }
            col.aggregate(pipeline).toCollection(mutableListOf()).asFlow()
        }.map { document ->
            val json = document.toJson()
            val jsonObject = Json.decodeFromString<JsonObject>(json)
            searchResults.add(decodeFromJsonElement(SearchResult.serializer(), jsonObject))
        }.catch {
            exceptionLogger.error("Error searching for $query", it)
        }.toList()
        val preferred = listOf("ancestry", "background", "class", "feat")
        searchResults.sortWith(
            compareBy<SearchResult>({ !preferred.contains(it.type) }, { it.type }, { -it.score!! })
        )

        return searchResults.toList()
    }
}
