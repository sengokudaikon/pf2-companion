package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.FeatEffectsRepository
import io.sengokudaikon.fixtureloader.fixtures.model.FeatEffectFixture

class FeatEffectLoader(
    val featEffectsRepository: FeatEffectsRepository,
) : FixtureLoader<FeatEffectFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<FeatEffectFixture> {
        TODO("Not yet implemented")
    }
}
