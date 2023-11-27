package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.FamiliarAbilityFixture
import io.sengokudaikon.isn.compendium.persistence.character.companion.repository.FamiliarAbilityRepository

class FamiliarAbilityLoader(private val familiarAbilityRepository: FamiliarAbilityRepository) :
    FixtureLoader<FamiliarAbilityFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<FamiliarAbilityFixture> {
        TODO("Not yet implemented")
    }
}
