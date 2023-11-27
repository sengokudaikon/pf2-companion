package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.VehicleFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.VehicleRepository

class VehicleLoader(private val vehicleRepository: VehicleRepository) : FixtureLoader<VehicleFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<VehicleFixture> {
        TODO("Not yet implemented")
    }
}
