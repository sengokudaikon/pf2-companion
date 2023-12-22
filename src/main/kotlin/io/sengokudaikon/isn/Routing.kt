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
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassFeatListHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassFeatNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassListHandler
import io.sengokudaikon.isn.compendium.adapters.character.classs.ClassNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ArmorListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ArmorNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.EquipmentListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.EquipmentNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ShieldListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.ShieldNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.WeaponListHandler
import io.sengokudaikon.isn.compendium.adapters.character.equipment.WeaponNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatListHandler
import io.sengokudaikon.isn.compendium.adapters.character.feat.GeneralFeatNameHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageListHandler
import io.sengokudaikon.isn.compendium.adapters.character.heritage.HeritageNameHandler
import io.sengokudaikon.isn.compendium.adapters.search.SearchHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionIdHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionListHandler
import io.sengokudaikon.isn.compendium.adapters.world.action.ActionNameHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.operations.character.background.query.BackgroundQuery
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.operations.character.heritage.query.HeritageQuery
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.errors.UserException
import org.koin.ktor.ext.inject
import kotlin.reflect.KClass
import io.ktor.server.resources.get as getResource
import io.ktor.server.resources.post as postResource

fun Application.configureRouting() {
    val ancestryListHandler: AncestryListHandler by inject()
    val ancestryIdHandler: AncestryIdHandler by inject()
    val ancestryNameHandler: AncestryNameHandler by inject()
    val backgroundListHandler: BackgroundListHandler by inject()
    val backgroundIdHandler: BackgroundIdHandler by inject()
    val backgroundNameHandler: BackgroundNameHandler by inject()
    val classListHandler: ClassListHandler by inject()
    val classIdHandler: ClassIdHandler by inject()
    val classNameHandler: ClassNameHandler by inject()
    val classFeatListHandler: ClassFeatListHandler by inject()
    val classFeatNameHandler: ClassFeatNameHandler by inject()
    val armorListHandler: ArmorListHandler by inject()
    val armorNameHandler: ArmorNameHandler by inject()
    val weaponListHandler: WeaponListHandler by inject()
    val weaponNameHandler: WeaponNameHandler by inject()
    val equipmentListHandler: EquipmentListHandler by inject()
    val equipmentNameHandler: EquipmentNameHandler by inject()
    val shieldListHandler: ShieldListHandler by inject()
    val shieldNameHandler: ShieldNameHandler by inject()
    val generalFeatListHandler: GeneralFeatListHandler by inject()
    val generalFeatIdHandler: GeneralFeatIdHandler by inject()
    val generalFeatNameHandler: GeneralFeatNameHandler by inject()
    val heritageListHandler: HeritageListHandler by inject()
    val heritageIdHandler: HeritageIdHandler by inject()
    val heritageNameHandler: HeritageNameHandler by inject()
    val actionListHandler: ActionListHandler by inject()
    val actionIdHandler: ActionIdHandler by inject()
    val actionNameHandler: ActionNameHandler by inject()
    val searchHandler: SearchHandler by inject()
    val registerHandler: RegisterHandler by inject()
    val emailExistHandler: EmailExistHandler by inject()
    val userExistHandler: UserExistHandler by inject()

    install(Resources)
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

        // compendium - character
        getResource<AncestryQuery.All> { ancestryListHandler.handle(call) }
        getResource<AncestryQuery.ById> { ancestryIdHandler.handle(call) }
        getResource<AncestryQuery.ByName> { ancestryNameHandler.handle(call) }
        getResource<BackgroundQuery.All> { backgroundListHandler.handle(call) }
        getResource<BackgroundQuery.ById> { backgroundIdHandler.handle(call) }
        getResource<BackgroundQuery.ByName> { backgroundNameHandler.handle(call) }
        getResource<ClassQuery.All> { classListHandler.handle(call) }
        getResource<ClassQuery.ById> { classIdHandler.handle(call) }
        getResource<ClassQuery.ByName> { classNameHandler.handle(call) }
        getResource<ClassFeatureQuery.All> { classFeatListHandler.handle(call) }
        getResource<ClassFeatureQuery.ByName> { classFeatNameHandler.handle(call) }
        getResource<ArmorQuery.All> { armorListHandler.handle(call) }
        getResource<ArmorQuery.ByName> { armorNameHandler.handle(call) }
        getResource<WeaponQuery.All> { weaponListHandler.handle(call) }
        getResource<WeaponQuery.ByName> { weaponNameHandler.handle(call) }
        getResource<EquipmentQuery.All> { equipmentListHandler.handle(call) }
        getResource<EquipmentQuery.ByName> { equipmentNameHandler.handle(call) }
        getResource<ShieldQuery.All> { shieldListHandler.handle(call) }
        getResource<ShieldQuery.ByName> { shieldNameHandler.handle(call) }
        getResource<FeatQuery.General.All> { generalFeatListHandler.handle(call) }
        getResource<FeatQuery.General.ById> { generalFeatIdHandler.handle(call) }
        getResource<FeatQuery.General.ByName> { generalFeatNameHandler.handle(call) }
        getResource<HeritageQuery.All> { heritageListHandler.handle(call) }
        getResource<HeritageQuery.ById> { heritageIdHandler.handle(call) }
        getResource<HeritageQuery.ByName> { heritageNameHandler.handle(call) }

        // compendium - world
        getResource<ActionQuery.All> { actionListHandler.handle(call) }
        getResource<ActionQuery.ById> { actionIdHandler.handle(call) }
        getResource<ActionQuery.ByName> { actionNameHandler.handle(call) }
        // base
        postResource<SearchQuery> { searchHandler.execute(call) }
        postResource<UserCommand.Create> { registerHandler.execute(call) }
        getResource<EmailExists> {
            emailExistHandler.execute(call)
        }
        options<EmailExists> { call.respondNullable(HttpStatusCode.OK) }
        getResource<UserExists> {
            userExistHandler.execute(call)
        }
        options<UserExists> { call.respondNullable(HttpStatusCode.OK) }

        // character
        getResource<CharacterQuery.All> {
            authorize<CharacterQuery.All>(call) {
                CharacterListDebugHandler().handle(call)
            }
        }
        getResource<CharacterQuery.ById> {
            authorize<CharacterQuery.ById>(call) {
                CharacterByIdHandler().handle(call)
            }
        }
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
