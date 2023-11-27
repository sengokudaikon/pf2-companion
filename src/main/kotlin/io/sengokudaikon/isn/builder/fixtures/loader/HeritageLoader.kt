package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.HeritageFixture
import io.sengokudaikon.isn.compendium.persistence.character.background.repository.HeritageRepository

class HeritageLoader(private val heritageRepository: HeritageRepository) : FixtureLoader<HeritageFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<HeritageFixture> {
        TODO("Not yet implemented")
    }
}
