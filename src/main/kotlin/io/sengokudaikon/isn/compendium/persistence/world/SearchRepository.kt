package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.client.model.Projections.fields
import com.mongodb.client.model.Projections.include
import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
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
    suspend fun searchAnything(query: String): List<SearchResult> {
        val collections = db.listCollectionNames().toList()
        val searchResults = collections.asFlow().buffer(1000).flatMapMerge { collection ->
            val col = db.getCollection<Document>(collection)
            val filter: Bson = Document("\$text", Document("\$search", query))
            val projection: Bson = fields(include("_id", "name", "img", "type", "system.description"))
            col.find(filter).projection(projection).limit(100).toCollection(mutableListOf()).asFlow()
        }.map { document ->
            val json = document.toJson()
            val jsonObject = Json.decodeFromString<JsonObject>(json)
            decodeFromJsonElement(SearchResult.serializer(), jsonObject)
        }.catch {
            exceptionLogger.error("Error searching for $query", it)
        }.toList()

        return searchResults.toList()
    }

    suspend fun searchAnythingBlocking(query: String): List<SearchResult> {
        val collections = db.listCollectionNames().toList()
        val results = mutableListOf<SearchResult>()
        try {
            for (collection in collections) {
                val col = db.getCollection<Document>(collection)
                val filter: Bson = Document("\$text", Document("\$search", query))
                val projection: Bson = fields(include("_id", "name", "img", "type", "system.description"))
                val documents = col.find(filter).projection(projection).limit(100).toCollection(mutableListOf())
                for (document in documents) {
                    val json = document.toJson()
                    val jsonObject = Json.decodeFromString<JsonObject>(json)
                    val model = decodeFromJsonElement(SearchResult.serializer(), jsonObject)
                    results.add(model)
                }
            }
        } catch (e: Exception) {
            exceptionLogger.error("Error searching for $query", e)
            println(e)
        }
        return results
    }
}
