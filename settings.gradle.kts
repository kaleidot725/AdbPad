pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.hq.hydraulic.software")
    }
    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        kotlin("plugin.serialization").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("org.jlleitschuh.gradle.ktlint").version(extra["library.ktlint.plugin"] as String)
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
            val adamVer = extra["library.adam"] as String
            library("adam", "com.malinskiy.adam:adam:$adamVer")
            val coroutinesVer = extra["kotlin.coroutines"] as String
            library("kotlin-coroutines", "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVer")
            val serializationVer = extra["kotlin.serialization"] as String
            library("kotlin-serialization", "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVer")
            val junitVer = extra["library.junit"] as String
            library("junit5", "org.junit.jupiter:junit-jupiter:$junitVer")
            val koin = extra["library.koin"] as String
            library("koin", "io.insert-koin:koin-core:$koin")
            val jSystemThemeDetectorVer = extra["library.system_theme_detector"] as String
            library("jSystemThemeDetectorVer", "com.github.Dansoftowner:jSystemThemeDetector:$jSystemThemeDetectorVer")
            val jetbrainsExpUiThemeVer = extra["library.jetbrains_expui_theme"] as String
            library("jetbrainsExpUiTheme", "com.bybutter.compose:compose-jetbrains-expui-theme:$jetbrainsExpUiThemeVer")
        }
    }
}

rootProject.name = "adbpad"
