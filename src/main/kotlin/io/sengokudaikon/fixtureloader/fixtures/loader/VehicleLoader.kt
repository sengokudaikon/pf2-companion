package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.VehicleRepository
import io.sengokudaikon.fixtureloader.fixtures.model.VehicleFixture

class VehicleLoader(private val vehicleRepository: VehicleRepository) : FixtureLoader<VehicleFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<VehicleFixture> {
        TODO("Not yet implemented")
    }
}
