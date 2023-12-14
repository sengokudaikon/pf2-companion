package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.client.model.Projections.fields
import com.mongodb.client.model.Projections.include
import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
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
            val filters = mutableListOf<Bson>()
            query.type?.let { filters.add(Document("type", it)) }
            query.traits?.let { filters.add(Document("system.traits.value", Document("\$all", it))) }
            query.rarity?.let { filters.add(Document("system.traits.rarity", Document("\$all", it))) }
            val filter: Bson = Document("\$and", filters)
            val projection: Bson = fields(include("_id", "name", "img", "type", "system"))
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
}
