package io.sengokudaikon.isn

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.sengokudaikon.isn.app.adapters.auth.EmailExistHandler
import io.sengokudaikon.isn.app.adapters.auth.RegisterHandler
import io.sengokudaikon.isn.app.adapters.auth.UserExistHandler
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.operations.user.query.EmailExists
import io.sengokudaikon.isn.app.operations.user.query.UserExists
import io.sengokudaikon.isn.chargen.adapters.character.read.CharacterByIdHandler
import io.sengokudaikon.isn.chargen.adapters.character.read.CharacterListDebugHandler
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryListHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.background.BackgroundIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.background.BackgroundListHandler
import io.sengokudaikon.isn.compendium.adapters.character.background.BackgroundNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassListHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ArmorIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ArmorListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ArmorNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.BackpackIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.BackpackListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.BackpackNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ConsumableIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ConsumableListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ConsumableNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.EquipmentIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.EquipmentListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.EquipmentNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.KitIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.KitListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.KitNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ShieldIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ShieldListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ShieldNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.TreasureIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.TreasureListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.TreasureNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.WeaponIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.WeaponListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.WeaponNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatListHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.SkillFeatIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.SkillFeatListHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.SkillFeatNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageListHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageNameHandler
import io.sengokudaikon.isn.compendium.adapters.search.SearchHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionListHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.bestiary.BestiaryIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.bestiary.BestiaryListHandler
import io.sengokudaikon.isn.compendium.adapters.world.bestiary.BestiaryNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.booncurse.BoonCurseIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.booncurse.BoonCurseListHandler
import io.sengokudaikon.isn.compendium.adapters.world.booncurse.BoonCurseNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.condition.ConditionIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.condition.ConditionListHandler
import io.sengokudaikon.isn.compendium.adapters.world.condition.ConditionNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.deity.DeityIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.deity.DeityListHandler
import io.sengokudaikon.isn.compendium.adapters.world.deity.DeityNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.hazard.HazardIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.hazard.HazardListHandler
import io.sengokudaikon.isn.compendium.adapters.world.hazard.HazardNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.spell.SpellIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.spell.SpellListHandler
import io.sengokudaikon.isn.compendium.adapters.world.spell.SpellNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.vehicle.VehicleIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.vehicle.VehicleListHandler
import io.sengokudaikon.isn.compendium.adapters.world.vehicle.VehicleNameHandler
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.KitQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.TreasureQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.operations.character.spell.query.SpellQuery
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.operations.world.booncurse.query.BoonCurseQuery
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.operations.world.vehicle.query.VehicleQuery
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.errors.UserException
import org.koin.ktor.ext.inject
import kotlin.reflect.KClass
import io.ktor.server.resources.get as getResource
import io.ktor.server.resources.post as postResource

fun Application.configureRouting() {
    val searchHandler: SearchHandler by inject()
    val registerHandler: RegisterHandler by inject()
    val emailExistHandler: EmailExistHandler by inject()
    val userExistHandler: UserExistHandler by inject()

    install(Resources)
    characterRoutes()
    equipmentRoutes()
    featRoutes()
    compendiumRoutes()
    routing {
        get("/") {
            call.respondText("Hello World!", contentType = ContentType.Text.Plain)
        }
        swaggerUI(path = "/api/docs", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
            configLoaders
        }

        get("/api/health") {
            call.respond(HttpStatusCode.OK, "Healthy")
        }

        // Search
        getResource<SearchQuery> { searchHandler.handle(call) }
        // Register
        postResource<UserCommand.Auth> { registerHandler.execute(call) }
        // Does email exist
        getResource<EmailExists> {
            emailExistHandler.execute(call)
        }
        options<EmailExists> { call.respondNullable(HttpStatusCode.OK) }
        // Does user exist
        getResource<UserExists> {
            userExistHandler.execute(call)
        }
        options<UserExists> { call.respondNullable(HttpStatusCode.OK) }

        // Characters
        getResource<CharacterQuery.All> {
            authorize<CharacterQuery.All>(call) {
                CharacterListDebugHandler().handle(call)
            }
        }
        // Character by ID
        getResource<CharacterQuery.ById> {
            authorize<CharacterQuery.ById>(call) {
                CharacterByIdHandler().handle(call)
            }
        }
    }
}

fun Application.compendiumRoutes() {
    val actionListHandler: ActionListHandler by inject()
    val actionIdHandler: ActionIdHandler by inject()
    val actionNameHandler: ActionNameHandler by inject()
    val spellListHandler: SpellListHandler by inject()
    val spellIdHandler: SpellIdHandler by inject()
    val spellNameHandler: SpellNameHandler by inject()
    val bestiaryListHandler: BestiaryListHandler by inject()
    val bestiaryIdHandler: BestiaryIdHandler by inject()
    val bestiaryNameHandler: BestiaryNameHandler by inject()
    val boonCurseListHandler: BoonCurseListHandler by inject()
    val boonCurseIdHandler: BoonCurseIdHandler by inject()
    val boonCurseNameHandler: BoonCurseNameHandler by inject()
    val conditionListHandler: ConditionListHandler by inject()
    val conditionIdHandler: ConditionIdHandler by inject()
    val conditionNameHandler: ConditionNameHandler by inject()
    val deityListHandler: DeityListHandler by inject()
    val deityIdHandler: DeityIdHandler by inject()
    val deityNameHandler: DeityNameHandler by inject()
    val hazardListHandler: HazardListHandler by inject()
    val hazardIdHandler: HazardIdHandler by inject()
    val hazardNameHandler: HazardNameHandler by inject()
    val vehicleListHandler: VehicleListHandler by inject()
    val vehicleIdHandler: VehicleIdHandler by inject()
    val vehicleNameHandler: VehicleNameHandler by inject()
    routing {
        // Actions
        getResource<ActionQuery.All> { actionListHandler.handle(call) }
        // Action by ID
        getResource<ActionQuery.ById> { actionIdHandler.handle(call) }
        // Action by Name
        getResource<ActionQuery.ByName> { actionNameHandler.handle(call) }
        // Bestiary
        getResource<BestiaryQuery.All> { bestiaryListHandler.handle(call) }
        // Bestiary by ID
        getResource<BestiaryQuery.ById> { bestiaryIdHandler.handle(call) }
        // Bestiary by Name
        getResource<BestiaryQuery.ByName> { bestiaryNameHandler.handle(call) }
        // Boons & Curses
        getResource<BoonCurseQuery.All> { boonCurseListHandler.handle(call) }
        // Boon & Curse by ID
        getResource<BoonCurseQuery.ById> { boonCurseIdHandler.handle(call) }
        // Boon & Curse by Name
        getResource<BoonCurseQuery.ByName> { boonCurseNameHandler.handle(call) }
        // Conditions
        getResource<ConditionQuery.All> { conditionListHandler.handle(call) }
        // Condition by ID
        getResource<ConditionQuery.ById> { conditionIdHandler.handle(call) }
        // Condition by Name
        getResource<ConditionQuery.ByName> { conditionNameHandler.handle(call) }
        // Deities
        getResource<DeityQuery.All> { deityListHandler.handle(call) }
        // Deity by ID
        getResource<DeityQuery.ById> { deityIdHandler.handle(call) }
        // Deity by Name
        getResource<DeityQuery.ByName> { deityNameHandler.handle(call) }
        // Hazards
        getResource<HazardQuery.All> { hazardListHandler.handle(call) }
        // Hazard by ID
        getResource<HazardQuery.ById> { hazardIdHandler.handle(call) }
        // Hazard by Name
        getResource<HazardQuery.ByName> { hazardNameHandler.handle(call) }
        // Spells
        getResource<SpellQuery.All> { spellListHandler.handle(call) }
        // Spell by ID
        getResource<SpellQuery.ById> { spellIdHandler.handle(call) }
        // Spell by Name
        getResource<SpellQuery.ByName> { spellNameHandler.handle(call) }
        // Vehicles
        getResource<VehicleQuery.All> { vehicleListHandler.handle(call) }
        // Vehicle by ID
        getResource<VehicleQuery.ById> { vehicleIdHandler.handle(call) }
        // Vehicle by Name
        getResource<VehicleQuery.ByName> { vehicleNameHandler.handle(call) }
    }
}

fun Application.featRoutes() {
    val generalFeatListHandler: GeneralFeatListHandler by inject()
    val generalFeatIdHandler: GeneralFeatIdHandler by inject()
    val generalFeatNameHandler: GeneralFeatNameHandler by inject()
    val skillFeatListHandler: SkillFeatListHandler by inject()
    val skillFeatIdHandler: SkillFeatIdHandler by inject()
    val skillFeatNameHandler: SkillFeatNameHandler by inject()
    routing {
        // General Feats
        getResource<FeatQuery.General.All> { generalFeatListHandler.handle(call) }
        // General Feat by ID
        getResource<FeatQuery.General.ById> { generalFeatIdHandler.handle(call) }
        // General Feat by Name
        getResource<FeatQuery.General.ByName> { generalFeatNameHandler.handle(call) }
        // Skill Feats
        getResource<FeatQuery.Skill.All> { skillFeatListHandler.handle(call) }
        // Skill Feat by ID
        getResource<FeatQuery.Skill.ById> { skillFeatIdHandler.handle(call) }
        // Skill Feat by Name
        getResource<FeatQuery.Skill.ByName> { skillFeatNameHandler.handle(call) }
    }
}

fun Application.equipmentRoutes(){
    val armorListHandler: ArmorListHandler by inject()
    val armorNameHandler: ArmorNameHandler by inject()
    val armorIdHandler: ArmorIdHandler by inject()
    val weaponListHandler: WeaponListHandler by inject()
    val weaponNameHandler: WeaponNameHandler by inject()
    val weaponIdHandler: WeaponIdHandler by inject()
    val equipmentListHandler: EquipmentListHandler by inject()
    val equipmentNameHandler: EquipmentNameHandler by inject()
    val equipmentIdHandler: EquipmentIdHandler by inject()
    val shieldListHandler: ShieldListHandler by inject()
    val shieldNameHandler: ShieldNameHandler by inject()
    val shieldIdHandler: ShieldIdHandler by inject()
    val treasureListHandler: TreasureListHandler by inject()
    val treasureNameHandler: TreasureNameHandler by inject()
    val treasureIdHandler: TreasureIdHandler by inject()
    val kitListHandler: KitListHandler by inject()
    val kitNameHandler: KitNameHandler by inject()
    val kitIdHandler: KitIdHandler by inject()
    val backpackListHandler: BackpackListHandler by inject()
    val backpackNameHandler: BackpackNameHandler by inject()
    val backpackIdHandler: BackpackIdHandler by inject()
    val consumableListHandler: ConsumableListHandler by inject()
    val consumableNameHandler: ConsumableNameHandler by inject()
    val consumableIdHandler: ConsumableIdHandler by inject()
    routing {
        // Armors
        getResource<ArmorQuery.All> { armorListHandler.handle(call) }
        // Armor by Name
        getResource<ArmorQuery.ByName> { armorNameHandler.handle(call) }
        // Armor by ID
        getResource<ArmorQuery.ById> { armorIdHandler.handle(call) }
        // Weapons
        getResource<WeaponQuery.All> { weaponListHandler.handle(call) }
        // Weapon by Name
        getResource<WeaponQuery.ByName> { weaponNameHandler.handle(call) }
        // Weapon by ID
        getResource<WeaponQuery.ById> { weaponIdHandler.handle(call) }
        // Equipment
        getResource<EquipmentQuery.All> { equipmentListHandler.handle(call) }
        // Equipment by Name
        getResource<EquipmentQuery.ByName> { equipmentNameHandler.handle(call) }
        // Equipment by ID
        getResource<EquipmentQuery.ById> { equipmentIdHandler.handle(call) }
        // Shields
        getResource<ShieldQuery.All> { shieldListHandler.handle(call) }
        // Shield by Name
        getResource<ShieldQuery.ByName> { shieldNameHandler.handle(call) }
        // Shield by ID
        getResource<ShieldQuery.ById> { shieldIdHandler.handle(call) }
        // Treasures
        getResource<TreasureQuery.All> { treasureListHandler.handle(call) }
        // Treasure by Name
        getResource<TreasureQuery.ByName> { treasureNameHandler.handle(call) }
        // Treasure by ID
        getResource<TreasureQuery.ById> { treasureIdHandler.handle(call) }
        // Kits
        getResource<KitQuery.All> { kitListHandler.handle(call) }
        // Kit by Name
        getResource<KitQuery.ByName> { kitNameHandler.handle(call) }
        // Kit by ID
        getResource<KitQuery.ById> { kitIdHandler.handle(call) }
        // Backpacks
        getResource<BackpackQuery.All> { backpackListHandler.handle(call) }
        // Backpack by Name
        getResource<BackpackQuery.ByName> { backpackNameHandler.handle(call) }
        // Backpack by ID
        getResource<BackpackQuery.ById> { backpackIdHandler.handle(call) }
        // Consumables
        getResource<ConsumableQuery.All> { consumableListHandler.handle(call) }
        // Consumable by Name
        getResource<ConsumableQuery.ByName> { consumableNameHandler.handle(call) }
        // Consumable by ID
        getResource<ConsumableQuery.ById> { consumableIdHandler.handle(call) }
    }
}

fun Application.characterRoutes() {
    val ancestryListHandler: AncestryListHandler by inject()
    val ancestryIdHandler: AncestryIdHandler by inject()
    val ancestryNameHandler: AncestryNameHandler by inject()
    val backgroundListHandler: BackgroundListHandler by inject()
    val backgroundIdHandler: BackgroundIdHandler by inject()
    val backgroundNameHandler: BackgroundNameHandler by inject()
    val classListHandler: ClassListHandler by inject()
    val classIdHandler: ClassIdHandler by inject()
    val classNameHandler: ClassNameHandler by inject()
    val heritageListHandler: HeritageListHandler by inject()
    val heritageIdHandler: HeritageIdHandler by inject()
    val heritageNameHandler: HeritageNameHandler by inject()
    routing {
        // Ancestries
        getResource<AncestryQuery.All> { ancestryListHandler.handle(call) }
        // Ancestry by ID
        getResource<AncestryQuery.ById> { ancestryIdHandler.handle(call) }
        // Ancestry by Name
        getResource<AncestryQuery.ByName> { ancestryNameHandler.handle(call) }
        // Backgrounds
        getResource<BackgroundQuery.All> { backgroundListHandler.handle(call) }
        // Background by ID
        getResource<BackgroundQuery.ById> { backgroundIdHandler.handle(call) }
        // Background by Name
        getResource<BackgroundQuery.ByName> { backgroundNameHandler.handle(call) }
        // Classes
        getResource<ClassQuery.All> { classListHandler.handle(call) }
        // Class by ID
        getResource<ClassQuery.ById> { classIdHandler.handle(call) }
        // Class by Name
        getResource<ClassQuery.ByName> { classNameHandler.handle(call) }

        // Heritages
        getResource<HeritageQuery.All> { heritageListHandler.handle(call) }
        // Heritage by ID
        getResource<HeritageQuery.ById> { heritageIdHandler.handle(call) }
        // Heritage by Name
        getResource<HeritageQuery.ByName> { heritageNameHandler.handle(call) }
    }
}

suspend inline fun <reified T : Any> authorize(call: ApplicationCall, crossinline handler: suspend () -> Unit) {
    val requiredRoles = T::class.getRequiredRoles()
        ?: return call.respond(HttpStatusCode.Forbidden, "Resource requires authentication.")

    call.authorize<T>(requiredRoles) {
        handler()
    }
}

fun <T : Any> KClass<T>.getRequiredRoles(): List<UserRole>? {
    return annotations.firstOrNull { it is AuthenticatedAPI || it is PrivateAPI }?.let { annotation ->
        when (annotation) {
            is AuthenticatedAPI -> listOf(UserRole.USER)
            is PrivateAPI -> listOf(UserRole.ADMIN, UserRole.OWNER)
            else -> null
        }
    }
}

suspend fun <T> ApplicationCall.authorize(
    requiredRoles: List<UserRole>,
    block: suspend () -> Unit,
) {
    val user = principal<FirebasePrincipal>()?.uid ?: throw UserException.Unauthorized("Unauthorized user")
    val userRepository: UserRepositoryPort by inject()
    val userRole =
        userRepository.findByUid(user).getOrNull()?.role ?: throw UserException.Unauthorized("Unauthorized user")
    if (userRole in requiredRoles) {
        block()
    } else {
        respond(HttpStatusCode.Forbidden, "You do not have the required role to access this resource.")
    }
}
