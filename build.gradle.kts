val ktorVersion: String by project
val koinVersion: String by project
val kotlinVersion: String by project
plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
    id("io.github.detekt.gradle.compiler-plugin") version "1.23.3"
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
    arg("KOIN_CONFIG_CHECK", "true")
}
dependencies {
    // di
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-core-coroutines:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")
    implementation("io.insert-koin:koin-test:$koinVersion")
    implementation("io.insert-koin:koin-annotations:1.3.0")

    // ktor
    api("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    api("io.ktor:ktor-server-auth:$ktorVersion")
    api("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-server-auto-head-response:$ktorVersion")
    implementation("io.ktor:ktor-server-double-receive:$ktorVersion")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")
    implementation("io.ktor:ktor-server-caching-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
    implementation("io.ktor:ktor-server-conditional-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-forwarded-header:$ktorVersion")
    implementation("io.ktor:ktor-server-hsts:$ktorVersion")
    implementation("io.ktor:ktor-server-http-redirect:$ktorVersion")
    implementation("io.ktor:ktor-server-swagger:$ktorVersion")
    implementation("io.ktor:ktor-server-partial-content:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-call-id:$ktorVersion")
    implementation("io.ktor:ktor-server-metrics:2.3.0")
    implementation("io.ktor:ktor-server-metrics-micrometer:$ktorVersion")
    implementation("io.micrometer:micrometer-registry-prometheus:1.12.0")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // db
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("io.github.crackthecodeabhi:kreds:0.9.0")
    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")
    implementation("org.mongodb:bson-kotlinx:4.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.15")

    ksp("io.insert-koin:koin-ksp-compiler:1.3.0")

    api("io.opentelemetry:opentelemetry-api:1.32.0")
    api("io.opentelemetry:opentelemetry-extension-kotlin:1.32.0")
    api("io.opentelemetry:opentelemetry-semconv:1.30.1-alpha")
    api("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:1.32.0")
    implementation("io.github.microutils:kotlin-logging-jvm:4.0.0-beta-2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")
    // tests
    implementation("io.mockk:mockk:1.13.5")
    implementation("io.cucumber:cucumber-jvm:7.15.0")
    implementation("io.cucumber:cucumber-gherkin:7.13.0")
    implementation("io.cucumber:cucumber-junit:7.13.0")
    implementation("io.cucumber:cucumber-java8:7.13.0")
    implementation("io.cucumber:cucumber-java:7.13.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.20")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.6")
    testImplementation("com.google.guava:guava:33.0.0-jre")
    testImplementation("io.kotest:kotest-property:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
    testImplementation("io.kotest.extensions:kotest-assertions-ktor:2.0.0")
    testImplementation("io.kotest:kotest-assertions-ktor-jvm:4.4.3")
    testImplementation("io.kotest.extensions:kotest-extensions-koin:1.3.0")
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom(files("$rootDir/detekt.yaml"))
    enableCompilerPlugin = false
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
configurations.all {
    resolutionStrategy.capabilitiesResolution.withCapability("com.google.guava:listenablefuture") {
        select("com.google.guava:guava:0")
    }
}
tasks.analyzeClassesDependencies {
    enabled = false
}
tasks.analyzeTestClassesDependencies {
    enabled = false
}
tasks.analyzeDependencies {
    enabled = false
}
