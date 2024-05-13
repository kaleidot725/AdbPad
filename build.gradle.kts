plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktlint)
}

group = "jp.kaleidot725"
version = "1.1.0"

repositories {
    mavenCentral()
    google()
    maven("https://packages.jetbrains.team/maven/p/kpm/public/")
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

kotlin {
    jvm {
        jvmToolchain {
            vendor = JvmVendorSpec.JETBRAINS
            languageVersion = JavaLanguageVersion.of(17)
        }
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
                implementation("org.jetbrains.jewel:jewel-int-ui-standalone-241:0.18.1")
                implementation("org.jetbrains.jewel:jewel-int-ui-decorated-window-241:0.18.1")
                implementation(compose.desktop.currentOs) { exclude(group = "org.jetbrains.compose.material") }
                implementation(libs.ktor.core)
                implementation(libs.ktor.client.okhttp)
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
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
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
