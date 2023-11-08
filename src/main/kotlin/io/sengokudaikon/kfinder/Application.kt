package io.sengokudaikon.kfinder

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.sengokudaikon.dbfinder.infrastructure.DbModule
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryFeatureRepository
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.dbfinder.persistence.character.background.repository.BackgroundRepository
import io.sengokudaikon.dbfinder.persistence.character.background.repository.HeritageRepository
import io.sengokudaikon.dbfinder.persistence.character.classs.repository.ClassFeatureRepository
import io.sengokudaikon.dbfinder.persistence.character.classs.repository.ClassRepository
import io.sengokudaikon.dbfinder.persistence.character.companion.repository.FamiliarAbilityRepository
import io.sengokudaikon.dbfinder.persistence.items.repository.EquipmentEffectRepository
import io.sengokudaikon.dbfinder.persistence.items.repository.EquipmentRepository
import io.sengokudaikon.dbfinder.persistence.items.repository.SpellEffectsRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.ActionRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.ConditionsRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.DeitiesRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.FeatEffectsRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.FeatRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.HazardRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.OtherEffectsRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.SpellRepository
import io.sengokudaikon.dbfinder.persistence.world.repository.VehicleRepository
import io.sengokudaikon.fixtureloader.fixtures.loader.ActionLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.AncestryFeatureLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.AncestryLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.BackgroundLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.ClassFeatureLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.ClassLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.ConditionLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.DeitiesLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.EquipmentEffectLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.EquipmentLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.FamiliarAbilityLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.FeatEffectLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.FeatLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.HazardLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.HeritageLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.OtherEffectsLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.SpellEffectLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.SpellLoader
import io.sengokudaikon.fixtureloader.fixtures.loader.VehicleLoader
import io.sengokudaikon.kfinder.infrastructure.CoreModule
import io.sengokudaikon.kfinder.infrastructure.DatabaseFactory
import io.sengokudaikon.kfinder.infrastructure.DbConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.ksp.generated.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val server = GlobalScope.launch {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            launch { module() }
        }.start(wait = true)
    }
    runBlocking {
        server.join()
    }
}

fun Application.module() {
    install(Koin) {
        modules(
            CoreModule().module,
            DbModule().module,
        )
    }

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
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    ktorRoutingAnnotationConfig()
    setupFixtures()
}

private fun Application.setupFixtures() {
    val featEffectRepository: FeatEffectsRepository by inject()
    val conditionRepository: ConditionsRepository by inject()
    val featRepository: FeatRepository by inject()
    val otherEffectRepository: OtherEffectsRepository by inject()
    val spellEffectRepository: SpellEffectsRepository by inject()
    val spellRepository: SpellRepository by inject()
    val classFeatureRepository: ClassFeatureRepository by inject()
    val classRepository: ClassRepository by inject()
    val ancestryFeatureRepository: AncestryFeatureRepository by inject()
    val ancestryRepository: AncestryRepository by inject()
    val actionRepository: ActionRepository by inject()
    val backgroundRepository: BackgroundRepository by inject()
    val heritageRepository: HeritageRepository by inject()
    val deityRepository: DeitiesRepository by inject()
    val equipmentEffectRepository: EquipmentEffectRepository by inject()
    val equipmentRepository: EquipmentRepository by inject()
    val familiarAbilityRepository: FamiliarAbilityRepository by inject()
    val hazardRepository: HazardRepository by inject()
    val vehicleRepository: VehicleRepository by inject()

    CoroutineScope(Dispatchers.IO).launch {
        FeatEffectLoader(featEffectRepository).insertIntoDatabase()
        ActionLoader(actionRepository).insertIntoDatabase()
        ConditionLoader(conditionRepository).insertIntoDatabase()
        FeatLoader(featRepository).insertIntoDatabase()
        OtherEffectsLoader(otherEffectRepository).insertIntoDatabase()
        SpellEffectLoader(spellEffectRepository).insertIntoDatabase()
        SpellLoader(spellRepository).insertIntoDatabase()
        ClassFeatureLoader(classFeatureRepository).insertIntoDatabase()
        ClassLoader(classRepository).insertIntoDatabase()
        AncestryFeatureLoader(ancestryFeatureRepository).insertIntoDatabase()
        AncestryLoader(ancestryRepository).insertIntoDatabase()
        BackgroundLoader(backgroundRepository).insertIntoDatabase()
        HeritageLoader(heritageRepository).insertIntoDatabase()
        DeitiesLoader(deityRepository).insertIntoDatabase()
        EquipmentEffectLoader(equipmentEffectRepository).insertIntoDatabase()
        EquipmentLoader(equipmentRepository).insertIntoDatabase()
        FamiliarAbilityLoader(familiarAbilityRepository).insertIntoDatabase()
        HazardLoader(hazardRepository).insertIntoDatabase()
        VehicleLoader(vehicleRepository).insertIntoDatabase()
    }
}
