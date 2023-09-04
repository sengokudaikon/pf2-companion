dependencies {
    implementation("io.ktor:ktor-server-status-pages:2.3.1")
}
plugins {
    `kotlin-dsl`
}
detekt {
    buildUponDefaultConfig = true
    parallel = true
    autoCorrect = true
    config.setFrom(files("$rootDir/detekt.yaml"))
}