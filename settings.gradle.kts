rootProject.name = "adbpad"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    versionCatalogs {
        create("libs") {
            library("adam", "com.malinskiy.adam:adam:0.4.5")
            library("kotlin-coroutines", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            library("kotlin-serialization", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
            library("junit5", "org.junit.jupiter:junit-jupiter:5.9.0")
        }
    }
}
