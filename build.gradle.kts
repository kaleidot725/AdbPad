plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
}

group = "jp.kaleidot725"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation(compose.desktop.currentOs)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(libs.adam)
                implementation(libs.kotlin.coroutines)
                implementation(libs.kotlin.serialization)
                implementation(libs.koin)
                implementation(libs.jSystemThemeDetectorVer)
                implementation(libs.jetbrainsExpUiTheme)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.junit5)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            packageName = "AdbPad"
            modules("jdk.management")
            modules("jdk.unsupported")

            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
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
