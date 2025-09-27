# Gemini Project Overview: AdbPad

## Project Overview

AdbPad is a desktop application for Windows and macOS that provides a graphical user interface (GUI) for interacting with Android devices using the Android Debug Bridge (ADB). It is built with Kotlin and uses Jetpack Compose for its user interface. The project follows a clean architecture, with a clear separation between the UI, domain, and data layers.

**Key Technologies:**

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose for Desktop
*   **Build Tool:** Gradle
*   **Architecture:** Model-View-Intent (MVI)
*   **Dependency Injection:** Koin
*   **Linter:** ktlint

## Building and Running

### Running the Application

To run the application with hot reload, use the following command:

```bash
./gradlew runHot
```

### Running Tests

To run the project's tests, use the following command:

```bash
./gradlew test
```

### Building the Application

To build the native installers for macOS and Windows, use the following commands:

**macOS (.dmg):**

```bash
./gradlew packageDmg
```

**Windows (.msi):**

```bash
./gradlew packageMsi
```

## Development Conventions

*   **Clean Architecture:** The project is structured into `ui`, `domain`, and `data` layers to ensure a separation of concerns.
*   **MVI Pattern:** The UI layer follows the Model-View-Intent pattern to manage state and user actions.
*   **Dependency Injection:** Koin is used for dependency injection to manage the lifecycle of objects.
*   **Code Style:** The project uses `ktlint` to enforce a consistent code style. You can run the linter with the following command:

    ```bash
    ./gradlew ktlintCheck
    ```
