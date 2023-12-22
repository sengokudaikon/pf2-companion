package io.sengokudaikon.isn.infrastructure

import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
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
            .append(dbConfig.url)
            .append("/")
            .append("?retryWrites=true&w=majority")
            .toString()
        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()
        val clientSettings = MongoClientSettings.builder()
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .applyConnectionString(com.mongodb.ConnectionString(uri))
            .serverApi(serverApi)
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
