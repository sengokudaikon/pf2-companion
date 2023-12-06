package io.sengokudaikon.isn.integration.compendium.repository

import com.mongodb.ConnectionString
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.persistence.world.ActionRepository
import io.sengokudaikon.isn.compendium.persistence.world.FeatEffectsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ActionRepositoryIntegrationTest {
    private val client = MongoClient.create(ConnectionString("mongodb://localhost:27017"))
    private val database = client.getDatabase("testDatabase")
    private val testCollection = database.getCollection<ActionModel>("actions")
    private val effectRepository = FeatEffectsRepository() // Assuming FeatEffectsRepository doesn't need any setup
    private val repository = ActionRepository(effectRepository).apply {
        this.collection = testCollection
    }

    @Test
    fun `findByName returns action when it exists`(): Unit = runBlocking {
        // Arrange
        val actionName = "Reconnoiter"
        // Act
        val result = repository.findByName(actionName)

        // Assert
        assertEquals(result.isSuccess, true)
    }
}
