package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.character.background.repository.HeritageRepository
import io.sengokudaikon.fixtureloader.fixtures.model.HeritageFixture

class HeritageLoader(private val heritageRepository: HeritageRepository) : FixtureLoader<HeritageFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<HeritageFixture> {
        TODO("Not yet implemented")
    }
}
