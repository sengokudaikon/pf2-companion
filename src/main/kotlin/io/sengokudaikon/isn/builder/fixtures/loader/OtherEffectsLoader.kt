package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.OtherEffectsFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.OtherEffectsRepository

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
