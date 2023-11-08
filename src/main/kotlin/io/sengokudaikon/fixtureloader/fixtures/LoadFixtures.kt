package io.sengokudaikon.fixtureloader.fixtures

import io.github.cdimascio.dotenv.dotenv
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.VisionSenseRepository
import io.sengokudaikon.dbfinder.persistence.character.background.repository.BackgroundRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.ActionRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.EffectsRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.FeatRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.LanguageRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.TraitRepository
import io.sengokudaikon.fixtureloader.fixtures.loader.web.LanguageFixtureLoader
import io.sengokudaikon.kfinder.infrastructure.DatabaseFactory
import io.sengokudaikon.kfinder.infrastructure.DbConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val dotenv = dotenv()
    val config = DbConfig(
        driver = dotenv["DB_DRIVER"],
        url = "jdbc:postgresql://"
            .plus(dotenv["DB_HOST"]).plus(":").plus(dotenv["DB_PORT"]).plus("/").plus(dotenv["DB_NAME"]),
        username = dotenv["DB_USER"],
        password = dotenv["DB_PASSWORD"],
    )
    val runMigrations = dotenv["RUN_MIGRATIONS"]?.toBoolean() ?: false
    DatabaseFactory.init(runMigrations = runMigrations, dbConfig = config)
    val languageRepository = LanguageRepository()
    val visionSenseRepository = VisionSenseRepository()
    val traitRepository = TraitRepository()
    val ancestryRepository = AncestryRepository(
        visionSenseRepository,
        languageRepository,
        traitRepository,
    )
    val actionRepository = ActionRepository(
        EffectsRepository(),
        traitRepository,
    )
    val backgroundRepository = BackgroundRepository(
        actionRepository = actionRepository,
        featRepository = FeatRepository(),
        traitRepository = TraitRepository(),
    )

    CoroutineScope(Dispatchers.IO).launch {
        LanguageFixtureLoader(languageRepository).insertIntoDatabase()
        //        VisionSenseFixtureLoader(visionSenseRepository).insertIntoDatabase()
        //        TraitFixtureLoader(traitRepository).insertIntoDatabase()
        //        ActionFixtureLoader(actionRepository).insertIntoDatabase()
        //        BackgroundFixtureLoader(backgroundRepository).insertIntoDatabase()
        //        AncestryFixtureLoader(ancestryRepository).insertIntoDatabase()
    }
}
