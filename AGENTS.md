# Repository Guidelines

## Project Structure & Module Organization
AdbPad is a Kotlin Multiplatform Compose Desktop app. The desktop entry point `jp.kaleidot725.adbpad.MainKt` lives in `main/kotlin`, where UI features are grouped under `ui/section/<area>` with companion `state/` packages. Shared MVI scaffolding sits in `core/mvi`, reusable helpers in `core/utils`, domain models and use cases in `domain`, and concrete clients plus repositories in `data`. Icons, entitlements, and packaging assets stay at the root, while Compose resources belong in `main/resources`.

## Build, Test, and Development Commands
- `./gradlew run`: Launches the JVM desktop app for manual testing.
- `./gradlew runHot`: Starts Compose Hot Reload (`runHot` task) for rapid UI iteration.
- `./gradlew build`: Compiles every module and assembles desktop artifacts into `build/`.
- `./gradlew test`: Executes JUnit 5 unit tests across modules.
- `./gradlew ktlintCheck` / `ktlintFormat`: Validates or fixes Kotlin style in all source sets.

## Coding Style & Naming Conventions
Use Kotlin defaults: 4-space indentation, trailing commas for multiline constructs, and wrap lines before 120 characters. Package paths stay lowercase by feature (`ui.section.top`). Compose functions are noun-phrases describing screens or widgets (`TopSection`, `DeviceSelector`), while the MVI triad follows `*StateHolder`, `*State`, `*Action`, and optional `*SideEffect`. Prefer immutable data classes, explicit visibility modifiers, and Koin module definitions near the features they provide. Always run ktlint before committing; `.editorconfig` already relaxes naming for annotated composables.

## Testing Guidelines
Place tests beside their modules in `test/kotlin`. Use JUnit 5 with `kotlinx.coroutines.test.runTest` when exercising state holders or asynchronous use cases. Name files `<ClassName>Test.kt` and test functions `methodName_behavior`. Mock adb or scrcpy integrations via in-memory fakes so unit tests stay deterministic. Cover new reducers by asserting both state transitions and emitted side effects, and document any manual device checks in the PR description.

## Commit & Pull Request Guidelines
Follow Conventional Commits as seen in history (`feat:`, `fix:`, `refactor:`, `chore:`) and keep subject lines under 72 characters. When a change spans modules, mention the most impacted scope (`feat: domain add device sync`). PRs must include: a clear summary, linked issues or tickets, local verification notes (`./gradlew test`, screenshots for UI changes), and a callout for new dependencies or configuration edits (e.g., adb path expectations). Request reviews from the relevant area owners and wait for CI to go green before merging.
