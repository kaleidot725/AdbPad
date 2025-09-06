plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
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
                implementation(libs.adam)
                implementation(libs.kotlin.coroutines)
                implementation(libs.kotlin.serialization)
                implementation(project(":domain"))
            }
        }
        val jvmTest by getting {
            kotlin.srcDirs("test/kotlin")
            resources.srcDirs("test/resources")
        }
    }
}
