package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.ConditionsRepository
import io.sengokudaikon.fixtureloader.fixtures.model.ConditionFixture

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
