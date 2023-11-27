package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.FeatEffectFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.FeatEffectsRepository

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
