![Language](https://img.shields.io/github/languages/top/its-me-debk007/kotlin-android-mvvm-template?color=B125EA&logo=kotlin&style=social)&nbsp;&nbsp;
[![Owner](https://img.shields.io/badge/by-thanhng224-brightgreen?logo=github&style=social)](https://github.com/ThanhNg224)&nbsp;&nbsp;
![License](https://img.shields.io/github/license/its-me-debk007/kotlin-android-mvvm-template.svg?style=social)

# Kotlin Android MVVM Template

A modern Jetpack Compose starter that already wires navigation, offline caching, onboarding/auth flows, and CI so you can focus on shipping features.

> ***"Every line of code should be written once, and only once." - Ron Jeffries***

## Contents 🎨

- [Feature Highlights](#feature-highlights-)
- [Architecture & Tech Stack](#architecture--tech-stack-)
- [App Flows](#app-flows-)
- [Project Setup](#project-setup-)
- [Build & Quality](#build--quality-)
- [Project Structure](#project-structure-)

## Feature Highlights 🎨

- 100% Jetpack Compose UI with animated floating bottom navigation and multiple screens (Home, Favorites, Profile, Settings).
- Onboarding + login gate that pick the correct start destination based on DataStore flags and login status.
- Sample authentication flow with remote login + persisted session state backed by DataStore preferences.
- Product feature with Retrofit + Kotlin Serialization calling `dummyjson.com`, Room caching, and graceful offline fallback.
- Clean MVVM + Clean Architecture layering (domain/use cases, data sources + repositories, presentation ViewModels + composables) and Kotlin DSL build scripts.
- Dependency injection with Hilt, coroutine dispatchers module, navigation-compose integration, and Gradle Version Catalog for dependency management.
- Detekt configuration for Compose, Dependabot schedule, and GitHub Actions pipeline for lint, tests, and APK build artifacts.

## Architecture & Tech Stack 🏗️

- **UI:** Jetpack Compose, Material 3, animated navigation bar, reusable dialogs, and screen-level state holders.
- **Navigation:** `navigation-compose` with a single-activity shell and feature destinations split per module.
- **Data:** Kotlinx Serialization + Retrofit + OkHttp logging for network, Room for offline-first caching, and DataStore Preferences for onboarding/auth flags and session data.
- **Domain:** Use cases encapsulate business logic and expose `Result`/`UiState` models to the UI.
- **DI:** Hilt modules provide API services, repositories, databases, and coroutine dispatchers with clear scopes.
- **Build:** Kotlin DSL scripts with Version Catalog, Gradle managed devices, and Detekt static analysis.


## App Flows 📱

The project includes a single module **`app`**, with following packages:
- **Onboarding ➜ Auth ➜ Home:** The app decides the start destination from DataStore flags and login state, then routes users through onboarding, login, and the main navigation graph automatically.
- **Product browsing:** Products are fetched from the sample API (`dummyjson.com` by default), cached in Room, and served offline when needed. Pull-to-refresh and error states are surfaced via Compose UI state holders.
- **Session handling:** Login writes the user session to DataStore so relaunching the app bypasses auth until logout or data reset.


## Build & Quality 🧪

- Build and install debug: `./gradlew :app:installDebug`
- Run unit tests: `./gradlew testDebugUnitTest`
- Run instrumented tests (use a running emulator/managed device): `./gradlew connectedDebugAndroidTest`
- Static analysis: `./gradlew detekt`
- Dependency updates and security: GitHub Actions workflow and Dependabot are configured; see `.github/workflows/android.yaml`.

## Project Structure 🗺️

```
app/
 ├─ core/              # Shared DI modules, Result/UiState, DataStore prefs
 ├─ feature/
 │   ├─ onboarding/    # DataStore-backed onboarding flow
 │   ├─ auth/          # Login use case + session persistence
 │   └─ product/       # Remote API + Room cache + UI state
 └─ presentation/      # App shell, navigation graph, theme, settings/profile stubs
```

Each feature follows **domain → data → presentation** layering with use cases, repositories, local/remote data sources, and Compose screens.

