@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktlint)
}

group = "jp.kaleidot725"
version = "1.5.2"

kotlin {
    jvm()
    jvmToolchain(17)

    composeCompiler {
        featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
    }

    sourceSets.jvmMain.dependencies {
        implementation(compose.desktop.currentOs)
        implementation(compose.desktop.components.splitPane)
        implementation(compose.material)
        implementation(compose.materialIconsExtended)
        implementation(libs.adam)
        implementation(libs.lucide)
        implementation(libs.kotlin.coroutines)
        implementation(libs.kotlinx.coroutines.swing)
        implementation(libs.kotlin.serialization)
        implementation(libs.koin)
        implementation(libs.ktor.core)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.jSystemThemeDetectorVer)
        implementation(libs.coil)
    }
    sourceSets.jvmTest.dependencies {
        implementation(libs.junit5)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        buildTypes.release {
            proguard.isEnabled = false
        }

        nativeDistributions {
            packageName = "AdbPad"
            modules("jdk.management")
            modules("jdk.unsupported")

            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe,
            )

            jvmArgs(
                "-Dapple.awt.application.appearance=NSAppearanceNameDarkAqua",
            )

            macOS {
                bundleID = "jp.kaleidot725.adbpad"
                iconFile.set(project.file("icon.icns"))
                entitlementsFile.set(project.file("default.entitlements"))
            }

            windows {
                iconFile.set(project.file("icon.ico"))
            }

            linux {
                iconFile.set(project.file("icon.ico"))
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

// build.gradle.kts
tasks.register<ComposeHotRun>("runHot") {
    mainClass.set("MainKt")
}
