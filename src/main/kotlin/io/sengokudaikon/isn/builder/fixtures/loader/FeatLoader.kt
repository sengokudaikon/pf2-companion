package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.FeatFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.FeatRepository

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
