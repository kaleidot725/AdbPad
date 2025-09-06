plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ktlint)
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}

kotlin {
    jvm()
    jvmToolchain(17)

    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("main/kotlin")
            resources.srcDirs("main/resources")
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlin.coroutines)
            }
        }
        val jvmTest by getting {
            kotlin.srcDirs("test/kotlin")
            resources.srcDirs("test/resources")
        }
    }
}
