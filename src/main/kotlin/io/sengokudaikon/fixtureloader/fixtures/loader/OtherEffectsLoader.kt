package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.OtherEffectsRepository
import io.sengokudaikon.fixtureloader.fixtures.model.OtherEffectsFixture

class OtherEffectsLoader(
    private val otherEffectsRepository: OtherEffectsRepository,
) : FixtureLoader<OtherEffectsFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<OtherEffectsFixture> {
        TODO("Not yet implemented")
    }
}
