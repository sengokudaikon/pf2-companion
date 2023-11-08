package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.FeatRepository
import io.sengokudaikon.fixtureloader.fixtures.model.FeatFixture

class FeatLoader(
    private val featRepository: FeatRepository,
) : FixtureLoader<FeatFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<FeatFixture> {
        TODO("Not yet implemented")
    }
}
