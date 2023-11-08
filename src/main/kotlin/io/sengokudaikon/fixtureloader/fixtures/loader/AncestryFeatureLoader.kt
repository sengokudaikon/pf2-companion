package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryFeatureRepository
import io.sengokudaikon.fixtureloader.fixtures.model.AncestryFeatureFixture

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
