package io.sengokudaikon.isn.infrastructure

import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Indexes
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList
import org.bson.Document
import org.bson.UuidRepresentation
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.kotlinx.KotlinSerializerCodecProvider

object DatabaseFactory {
    lateinit var client: MongoClient
    lateinit var database: MongoDatabase
    fun init(dbConfig: DbConfig) {
        val uri = StringBuilder()
            .append("mongodb://")
            .append(dbConfig.host)
            .append(":")
            .append(dbConfig.port)
            .append("/")
            .append(dbConfig.name)
            .toString()
        val clientSettings = MongoClientSettings.builder()
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .applyConnectionString(com.mongodb.ConnectionString(uri))
            .codecRegistry(getCodecRegistry())
            .build()
        client = MongoClient.create(clientSettings)
        database = client.getDatabase(dbConfig.name)
    }

    suspend fun createIndexes() {
        val collections = database.listCollectionNames().toList()
        collections.forEach { collection ->
            val col = database.getCollection<Document>(collection)
            val indexInfo = col.listIndexes().toList()
            val indexExists = indexInfo.any { index ->
                index["key"] == Document(
                    mapOf("_id" to 1, "name" to 1, "img" to 1, "type" to 1, "system.description" to 1),
                )
            }
            if (!indexExists) {
                col.createIndex(Indexes.ascending("_id", "name", "img", "type", "system.description"))
            }

            val fullTextIndexExists = indexInfo.any { index ->
                index["key"] == Document(mapOf("$**" to "text"))
            }
            if (!fullTextIndexExists) {
                col.createIndex(Indexes.text("$**"))
            }
        }
    }

    fun getCodecRegistry(): CodecRegistry {
        return fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(KotlinSerializerCodecProvider()),
        )
    }
}

inline fun <reified T : Any> getCollection(name: String) = DatabaseFactory.database.getCollection<T>(name)
