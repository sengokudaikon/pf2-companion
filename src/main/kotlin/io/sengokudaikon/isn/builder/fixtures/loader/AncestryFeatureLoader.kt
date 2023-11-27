package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.AncestryFeatureFixture
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.repository.AncestryFeatureRepository

class AncestryFeatureLoader(
    private val ancestryFeatureRepository: AncestryFeatureRepository,
) : FixtureLoader<AncestryFeatureFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<AncestryFeatureFixture> {
        TODO("Not yet implemented")
    }
}
