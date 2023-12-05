package io.sengokudaikon.isn.unit.compendium.repository

import com.mongodb.kotlin.client.coroutine.FindFlow
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.mockk.every
import io.mockk.mockk
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.persistence.world.ActionRepository
import io.sengokudaikon.isn.compendium.persistence.world.FeatEffectsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ActionRepositoryTest {
    private val mockCollection: MongoCollection<ActionModel> = mockk()
    private val mockEffectRepository: FeatEffectsRepository = mockk()
    private val repository = ActionRepository(mockEffectRepository).apply {
        collection = mockCollection
    }

    @Test
    fun `findByName returns action when it exists`() = runBlocking {
        // Arrange
        val actionName = "testAction"
        val expectedAction = mockk<ActionModel>().apply {
            every { name } returns actionName
        }
        every { mockCollection.find(any(), any()) } returns flowOf(expectedAction) as FindFlow<ActionModel>
        // Act
        val result = repository.findByName(actionName)

        // Assert
        assertEquals(Result.success(expectedAction), result)
    }
}
