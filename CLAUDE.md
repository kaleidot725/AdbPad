# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

AdbPad is a cross-platform GUI application for testing Android apps using ADB (Android Debug Bridge). Built with Kotlin Multiplatform and Compose Desktop, it provides functionality for device management, screenshot capture, and command execution.

## Development Commands

### Build & Run
- `./gradlew run` - Run the application in development mode
- `./gradlew runHot` - Run with hot reload enabled for development
- `./gradlew devRun` - Run in dev mode with hot reload
- `./gradlew build` - Build the project
- `./gradlew clean` - Clean build artifacts

### Testing
- `./gradlew test` - Run all tests
- `./gradlew jvmTest` - Run JVM-specific tests

### Code Quality
- `./gradlew ktlintCheck` - Run Kotlin linting
- `./gradlew ktlintFormat` - Format Kotlin code
- `./gradlew check` - Run all verification tasks

### Packaging
- `./gradlew createDistributable` - Create distributable package
- `./gradlew packageDistributionForCurrentOS` - Package for current OS
- `./gradlew packageDmg` - Create macOS DMG (macOS only)
- `./gradlew packageMsi` - Create Windows MSI (Windows only)

## Architecture Overview

### Core Architecture Pattern
The project follows an MVI (Model-View-Intent) architecture with the following components:

- **MVIBase**: Abstract base class for all state holders (`src/jvmMain/kotlin/jp/kaleidot725/adbpad/core/mvi/MVIBase.kt`)
- **State Holders**: Manage UI state and business logic for each screen
- **Use Cases**: Encapsulate specific business operations
- **Repositories**: Handle data access and external API communication

### Project Structure
- `src/jvmMain/kotlin/jp/kaleidot725/adbpad/`
  - `core/`: Core framework classes (MVI, DI modules)
  - `domain/`: Business logic (models, repositories, use cases)
  - `ui/`: User interface components and screens
  - `data/`: Data layer implementations

### Multi-module Structure
- Main application in `src/jvmMain/`
- Shared data module in `data/` with its own source structure

### Key Technologies
- **Kotlin Multiplatform**: Target platform abstraction
- **Compose Desktop**: UI framework
- **Koin**: Dependency injection
- **Adam**: ADB client library for Android device communication
- **Kotlinx Coroutines**: Asynchronous programming
- **Kotlinx Serialization**: Data serialization

### Screen Categories
The application is organized into main categories:
- **Command**: Execute ADB shell commands
- **Text**: Send text input to devices
- **Screenshot**: Capture and view device screenshots
- **Device**: Manage connected Android devices
- **File**: File operations (placeholder)

### State Management
Each screen follows the MVI pattern with:
- `*State`: Data class representing UI state
- `*Action`: Sealed class for user interactions
- `*SideEffect`: One-time events
- `*StateHolder`: Manages state transitions and business logic

### Dependency Injection
Uses Koin with three main modules:
- `repositoryModule`: Data layer dependencies
- `domainModule`: Use case dependencies
- `stateHolderModule`: UI state holder dependencies

## Development Notes

- Java 17 toolchain is required
- The application uses Material Design components
- Hot reload is available for rapid development with `runHot` task
- KtLint is configured for code formatting - run before committing
- ProGuard is disabled for release builds
- Cross-platform packaging supports Windows (MSI/EXE), macOS (DMG), and Linux (DEB)