import io.github.cdimascio.dotenv.Dotenv

plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("jacoco")
    id("org.flywaydb.flyway") version "9.22.1"
}

group = "io.sengokudaikon.kfinder"
version = "0.0.1"
buildscript {
    dependencies {
        classpath("io.github.cdimascio:java-dotenv:5.2.2")
    }
}

val dotenv = Dotenv.configure().ignoreIfMissing().load()
val flywayUrl = "jdbc:postgresql://" + dotenv["DB_HOST"] + ":" + dotenv["DB_PORT"] + "/" + dotenv["DB_NAME"]
val flywayUser = dotenv["DB_USER"]
val flywayPassword = dotenv["DB_PASSWORD"]

flyway {
    url = flywayUrl
    user = flywayUser
    password = flywayPassword
}
application {
    mainClass.set("io.sengokudaikon.kfinder.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

ktor {
    fatJar {
        archiveFileName.set("pf2companion.jar")
    }
}
ksp {
    allowSourcesFromOtherPlugins = true
    arg("kapt.kotlin.generated", "build/generated/ksp/main/kotlin")
}
dependencies {
    // arrow & di
    implementation("io.insert-koin:koin-test:3.3.3")
    implementation("com.github.dimitark.ktor-annotations:annotations:0.0.3")
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.40")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")
    implementation("io.ktor:ktor-server-status-pages:2.3.1")
    implementation("io.arrow-kt:arrow-continuations:1.2.0")
    implementation("io.insert-koin:koin-ktor:3.3.1")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("de.nycode:bcrypt:2.3.0")
    implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
    implementation("io.insert-koin:koin-annotations:1.1.1")
    implementation("io.arrow-kt:arrow-core:1.2.0-RC")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
    implementation("app.softwork:kotlinx-uuid-exposed:0.0.17")
    // db
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.testng:testng:7.7.0")
    implementation("org.flywaydb:flyway-core:9.16.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.bouncycastle:bcprov-jdk18on:1.72")
    implementation("net.bytebuddy:byte-buddy:1.14.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    implementation("app.softwork:kotlinx-uuid-core:0.0.19")
    // native
    api("io.ktor:ktor-server-core:2.3.1")
    implementation("io.ktor:ktor-server-netty:2.3.1")
    api("org.jetbrains.kotlin:kotlin-reflect:1.8.20-RC")
    api("io.ktor:ktor-server-auth:2.3.1")
    api("io.ktor:ktor-server-auth-jwt:2.3.1")
    implementation("io.ktor:ktor-server-sessions:2.3.1")
    implementation("io.ktor:ktor-server-auto-head-response:2.3.1")
    implementation("io.ktor:ktor-server-double-receive:2.3.1")
    implementation("io.ktor:ktor-server-resources:2.3.1")
    implementation("io.ktor:ktor-server-caching-headers:2.3.1")
    implementation("io.ktor:ktor-server-compression:2.3.1")
    implementation("io.ktor:ktor-server-conditional-headers:2.3.1")
    implementation("io.ktor:ktor-server-cors:2.3.1")
    implementation("io.ktor:ktor-server-default-headers:2.3.1")
    implementation("io.ktor:ktor-server-forwarded-header:2.3.1")
    implementation("io.ktor:ktor-server-hsts:2.3.1")
    implementation("io.ktor:ktor-server-http-redirect:2.3.1")
    implementation("io.ktor:ktor-server-openapi:2.3.1")
    implementation("io.ktor:ktor-server-swagger:2.3.1")
    implementation("io.ktor:ktor-server-partial-content:2.3.1")
    implementation("io.ktor:ktor-server-call-logging:2.3.1")
    implementation("io.ktor:ktor-server-call-id:2.3.1")
    implementation("io.ktor:ktor-server-metrics:2.3.0")
    implementation("io.ktor:ktor-server-metrics-micrometer:2.3.1")
    implementation("io.micrometer:micrometer-registry-prometheus:1.10.5")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation("io.ktor:ktor-server-config-yaml:2.3.4")
    implementation("io.github.classgraph:classgraph:4.8.161")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.7.2")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.20-RC")
    ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0")
    ksp("com.github.dimitark.ktor-annotations:processor:0.0.3")
    api("io.konform:konform-jvm:0.4.0")
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("com.vdurmont:semver4j:3.1.0")
    api("io.ktor:ktor-client-cio-jvm:2.3.1")
    api("io.ktor:ktor-client-logging-jvm:2.3.1")
    api("io.ktor:ktor-client-serialization-jvm:2.3.1")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
    api("io.opentelemetry:opentelemetry-api:1.28.0")
    api("io.opentelemetry:opentelemetry-extension-kotlin:1.28.0")
    api("io.opentelemetry:opentelemetry-semconv:1.28.0-alpha")
    api("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:1.28.0")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    // =============== utils - general ===============
    implementation("com.github.kittinunf.result:result:5.4.0")
    implementation("com.github.kittinunf.result:result-coroutines:4.0.0")
    implementation("org.apache.commons:commons-text:1.10.0")
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom(files("$rootDir/detekt.yaml"))
}
