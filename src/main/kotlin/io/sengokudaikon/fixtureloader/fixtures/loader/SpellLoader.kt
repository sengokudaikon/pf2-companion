package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.SpellRepository
import io.sengokudaikon.fixtureloader.fixtures.model.SpellFixture

class SpellLoader(
    private val spellRepository: SpellRepository,
) : FixtureLoader<SpellFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<SpellFixture> {
        TODO("Not yet implemented")
    }
}
