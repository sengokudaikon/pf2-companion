plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("jacoco")
}

group = "io.sengokudaikon"
version = "0.0.1"

detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config = files("$rootDir/detekt.yaml")
}

buildscript {
    dependencies {
        classpath("io.github.cdimascio:java-dotenv:5.2.2")
    }
}

ktor {
    fatJar {
        archiveFileName.set("pf2companion.jar")
    }
}
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
application {
    mainClass.set("io.sengokudaikon.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }

    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply(plugin = "com.google.devtools.ksp")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "jacoco")

    repositories {
        flatDir{
            dirs("../libs")
            dirs("libs")
        }
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        implementation("io.arrow-kt:arrow-continuations:1.2.0")
        ksp("io.insert-koin:koin-ksp-compiler:1.2.2")
        ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.2.0")
        ksp("com.github.dimitark.ktor-annotations:processor:0.0.3")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.exposed:exposed-core:0.40.1")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
        implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
        implementation("app.softwork:kotlinx-uuid-exposed:0.0.17")
        implementation("app.softwork:kotlinx-uuid-core:0.0.18")
        implementation("io.ktor:ktor-server-core:2.3.1")
        implementation("io.ktor:ktor-server-cio:2.3.1")
        implementation("io.ktor:ktor-server-auth:2.3.1")
        implementation("io.ktor:ktor-server-auth-jwt:2.3.1")
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
        //
        implementation("io.insert-koin:koin-ktor:3.3.1")
        implementation("io.insert-koin:koin-core:3.3.3")
        implementation("io.insert-koin:koin-test:3.3.3")
        implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
        implementation("io.insert-koin:koin-annotations:1.1.1")
        implementation("io.arrow-kt:arrow-core:1.2.0-RC")
        implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0-RC")
        implementation("com.github.dimitark.ktor-annotations:annotations:0.0.3")
        implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.38")
        implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")
        implementation("io.ktor:ktor-server-status-pages:2.3.1")
        testImplementation("io.ktor:ktor-server-tests:2.3.1")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.20-RC")
        implementation("graphql-kotlin-server:7.0.0-alpha.5@jar")
        implementation("graphql-kotlin-ktor-server:7.0.0-alpha.6@jar")
    }
}

tasks {
    build {
        dependsOn("detekt")
    }
}
tasks.shadowJar {
    setProperty("zip64", true)
}

tasks.test {
    useJUnitPlatform()
    //    finalizedBy("jacocoTestReport") // This ensures the test report is generated after the test task is executed
}

tasks.jacocoTestReport {
    reports {
        xml.required
        html.required
    }
}

jacoco {
    toolVersion = "0.8.7"
}