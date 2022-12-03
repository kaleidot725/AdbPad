plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
    id("dev.hydraulic.conveyor")
}

group = "jp.kaleidot725"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
    }
    sourceSets {
        all {
            dependencies {
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
        val jvmMain by getting {
            dependencies {
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

dependencies {
    linuxAmd64(compose.desktop.linux_x64)
    macAmd64(compose.desktop.macos_x64)
    macAarch64(compose.desktop.macos_arm64)
    windowsAmd64(compose.desktop.windows_x64)
}

// region Work around temporary Compose bugs.
configurations.all {
    attributes {
        // https://github.com/JetBrains/compose-jb/issues/1404#issuecomment-1146894731
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}

// Force override the Kotlin stdlib version used by Compose to 1.7, as otherwise we can end up with a mix of 1.6 and 1.7 on our classpath.
dependencies {
    val v = "1.7.10"
    for (m in setOf("linuxAmd64", "macAmd64", "macAarch64", "windowsAmd64")) {
        m("org.jetbrains.kotlin:kotlin-stdlib:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$v")
    }
}
// endregion
