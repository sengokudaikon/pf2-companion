import io.github.cdimascio.dotenv.Dotenv

plugins {
    `kotlin-dsl`
    id("org.flywaydb.flyway") version "7.15.0"
}

val dotenv = Dotenv.configure().ignoreIfMissing().load()
val flywayUrl = dotenv["FLYWAY_URL"]
val flywayUser = dotenv["FLYWAY_USER"]
val flywayPassword = dotenv["FLYWAY_PASSWORD"]

flyway {
    url = flywayUrl
    user = flywayUser
    password = flywayPassword
}

dependencies {
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("de.nycode:bcrypt:2.3.0")
    implementation("org.testng:testng:7.7.0")
    implementation("org.flywaydb:flyway-core:9.16.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.insert-koin:koin-ktor:3.3.1")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("io.insert-koin:koin-test:3.3.3")
    implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
    implementation("io.insert-koin:koin-annotations:1.1.1")
    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("org.bouncycastle:bcprov-jdk18on:1.72")
    implementation("com.github.dimitark.ktor-annotations:annotations:0.0.3")
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.38")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")
    implementation("net.bytebuddy:byte-buddy:1.14.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    implementation("io.ktor:ktor-server-status-pages:2.3.1")
}
