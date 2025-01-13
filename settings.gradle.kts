pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(file("../..//build/repo"))
        maven("https://packages.jetbrains.team/maven/p/firework/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(file("../..//build/repo"))
        maven("https://packages.jetbrains.team/maven/p/firework/dev")
        maven("https://jitpack.io")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "adbpad"
