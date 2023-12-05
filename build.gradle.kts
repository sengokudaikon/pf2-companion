plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("jacoco")
    id("org.flywaydb.flyway") version "9.22.1"
    id("ca.cutterslade.analyze") version "1.9.1"
}

group = "io.sengokudaikon.isn-backend"
version = "0.0.1"
buildscript {
    dependencies {
        classpath("io.github.cdimascio:java-dotenv:5.2.2")
    }
}

application {
    mainClass.set("io.sengokudaikon.isn.ApplicationKt")

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
        archiveFileName.set("pf2isn.jar")
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
    implementation("io.insert-koin:koin-ktor:3.3.1")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
    implementation("io.insert-koin:koin-annotations:1.1.1")
    // ktor
    api("io.ktor:ktor-server-core:2.3.1")
    implementation("io.ktor:ktor-server-netty:2.3.1")
    implementation("org.mongodb:bson-kotlinx:4.11.0")
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
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.5")
    implementation("io.github.crackthecodeabhi:kreds:0.9.0")
    implementation("com.google.firebase:firebase-admin:9.1.1")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.mockk:mockk:1.13.5")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.20-RC")
    ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
    ksp("com.github.dimitark.ktor-annotations:processor:0.0.3")
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("com.vdurmont:semver4j:3.1.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
    api("io.opentelemetry:opentelemetry-api:1.28.0")
    api("io.opentelemetry:opentelemetry-extension-kotlin:1.28.0")
    api("io.opentelemetry:opentelemetry-semconv:1.28.0-alpha")
    api("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:1.28.0")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom(files("$rootDir/detekt.yaml"))
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.shadowJar {
    isZip64 = true
}
