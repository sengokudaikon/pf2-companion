package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.character.companion.repository.FamiliarAbilityRepository
import io.sengokudaikon.fixtureloader.fixtures.model.FamiliarAbilityFixture

class FamiliarAbilityLoader(private val familiarAbilityRepository: FamiliarAbilityRepository) :
    FixtureLoader<FamiliarAbilityFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<FamiliarAbilityFixture> {
        TODO("Not yet implemented")
    }
}
