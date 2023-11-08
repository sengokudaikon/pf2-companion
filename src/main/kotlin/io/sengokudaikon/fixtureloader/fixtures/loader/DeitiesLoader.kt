package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.DeitiesRepository
import io.sengokudaikon.fixtureloader.fixtures.model.DeityFixture

class DeitiesLoader(private val deitiesRepository: DeitiesRepository) : FixtureLoader<DeityFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<DeityFixture> {
        TODO("Not yet implemented")
    }
}
