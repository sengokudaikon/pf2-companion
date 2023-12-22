package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.client.model.Filters.*
import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.operations.search.dto.Comparison
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.exceptionLogger
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
import org.bson.conversions.Bson
import org.koin.core.annotation.Single

@Single
class SearchRepository {
    val db = DatabaseFactory.database

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun searchAnything(query: SearchQuery): List<SearchResult> {
        val collections = db.listCollectionNames().toList()
        val searchResults = mutableListOf<SearchResult>()
        collections.asFlow().buffer(1000).flatMapMerge { collection ->
            val col = db.getCollection<Document>(collection)
            val bsonFilters = mutableListOf<Bson>()
            query.filters.forEach {
                val bsonFilter = when(it.comparison) {
                    Comparison.EQUALS -> eq(it.key.name.lowercase(), it.value)
                    Comparison.NOT_EQUALS -> ne(it.key.name.lowercase(), it.value)
                    Comparison.GREATER_THAN -> gt(it.key.name.lowercase(), it.value)
                    Comparison.LESS_THAN -> lt(it.key.name.lowercase(), it.value)
                    Comparison.GREATER_THAN_OR_EQUAL_TO -> gte(it.key.name.lowercase(), it.value)
                    Comparison.LESS_THAN_OR_EQUAL_TO -> lte(it.key.name.lowercase(), it.value)
                    Comparison.IN -> `in`(it.key.name.lowercase(), it.value)
                }
                bsonFilters.add(bsonFilter)
            }
            val matchStage = if (bsonFilters.isEmpty()) null else Document("\$match", and(bsonFilters))
            val pipeline = mutableListOf<Document>().apply {
                matchStage?.let { add(it) }
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

        searchResults.sortWith(compareBy<SearchResult>( { !preferred.contains(it.type) }, { it.type }, { -it.score!! }))

        return searchResults.toList()
    }
}
