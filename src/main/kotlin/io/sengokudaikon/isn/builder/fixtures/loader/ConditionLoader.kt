package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.ConditionFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.ConditionsRepository

class ConditionLoader(
    private val conditionsRepository: ConditionsRepository,
) : FixtureLoader<ConditionFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<ConditionFixture> {
        TODO("Not yet implemented")
    }
}
