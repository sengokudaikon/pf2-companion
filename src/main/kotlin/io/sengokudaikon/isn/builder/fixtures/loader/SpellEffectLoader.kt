package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.SpellEffectFixture
import io.sengokudaikon.isn.compendium.persistence.items.repository.SpellEffectsRepository

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
