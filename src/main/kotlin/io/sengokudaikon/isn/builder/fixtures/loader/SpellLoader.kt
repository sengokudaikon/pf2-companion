package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.SpellFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.SpellRepository

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
