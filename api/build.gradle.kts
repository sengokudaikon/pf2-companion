
plugins {
    `kotlin-dsl`
}
dependencies {
    implementation(project(":core"))
    implementation("io.ktor:ktor-auth-jwt:1.6.8")
}

detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom(files("$rootDir/detekt.yaml"))
}