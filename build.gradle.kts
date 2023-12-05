val ktor_version: String by project
val koin_version: String by project
plugins {
    kotlin("jvm") version "1.9.10"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
    id("jacoco")
    id("ca.cutterslade.analyze") version "1.9.1"
    id("com.github.ben-manes.versions") version "0.50.0"
}

group = "io.sengokudaikon.isn-backend"
version = "0.0.1"

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
    implementation("com.github.dimitark.ktor-annotations:annotations:0.0.3")
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.40")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-core-coroutines:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.insert-koin:koin-test:$koin_version")
    implementation("io.insert-koin:koin-annotations:1.3.0")

    // ktor
    api("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("org.mongodb:bson-kotlinx:4.11.0")
    api("org.jetbrains.kotlin:kotlin-reflect:1.8.20-RC")
    api("io.ktor:ktor-server-auth:$ktor_version")
    api("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-auto-head-response:$ktor_version")
    implementation("io.ktor:ktor-server-double-receive:$ktor_version")
    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("io.ktor:ktor-server-caching-headers:$ktor_version")
    implementation("io.ktor:ktor-server-compression:$ktor_version")
    implementation("io.ktor:ktor-server-conditional-headers:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-forwarded-header:$ktor_version")
    implementation("io.ktor:ktor-server-hsts:$ktor_version")
    implementation("io.ktor:ktor-server-http-redirect:$ktor_version")
    implementation("io.ktor:ktor-server-swagger:$ktor_version")
    implementation("io.ktor:ktor-server-partial-content:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-server-call-id:$ktor_version")
    implementation("io.ktor:ktor-server-metrics:2.3.0")
    implementation("io.ktor:ktor-server-metrics-micrometer:$ktor_version")
    implementation("io.micrometer:micrometer-registry-prometheus:1.12.0")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("io.github.crackthecodeabhi:kreds:0.9.0")
    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.mockk:mockk:1.13.5")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.20-RC")
    ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
    ksp("com.github.dimitark.ktor-annotations:processor:0.0.3")

    api("io.opentelemetry:opentelemetry-api:1.32.0")
    api("io.opentelemetry:opentelemetry-extension-kotlin:1.32.0")
    api("io.opentelemetry:opentelemetry-semconv:1.30.1-alpha")
    api("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:1.32.0")
    implementation("io.github.microutils:kotlin-logging:4.0.0-beta-2")
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
