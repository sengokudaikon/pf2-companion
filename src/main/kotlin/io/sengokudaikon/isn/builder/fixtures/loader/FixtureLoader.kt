package io.sengokudaikon.isn.builder.fixtures.loader

interface FixtureLoader<T> {
    suspend fun insertIntoDatabase()
    suspend fun setUpFixtures(): List<T>
}
