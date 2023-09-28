package io.sengokudaikon.dbfinder.fixtures

interface FixtureLoader<T> {
    suspend fun insertIntoDatabase()
    suspend fun setUpFixtures(): List<T>
}
