package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.DeityFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.DeitiesRepository

class DeitiesLoader(private val deitiesRepository: DeitiesRepository) : FixtureLoader<DeityFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<DeityFixture> {
        TODO("Not yet implemented")
    }
}
