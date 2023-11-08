package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.items.repository.SpellEffectsRepository
import io.sengokudaikon.fixtureloader.fixtures.model.SpellEffectFixture

class SpellEffectLoader(
    private val spellEffectsRepository: SpellEffectsRepository,
) : FixtureLoader<SpellEffectFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<SpellEffectFixture> {
        TODO("Not yet implemented")
    }
}
