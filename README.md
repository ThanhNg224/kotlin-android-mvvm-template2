![Language](https://img.shields.io/github/languages/top/its-me-debk007/kotlin-android-mvvm-template?color=B125EA&logo=kotlin&style=social)&nbsp;&nbsp;
[![Owner](https://img.shields.io/badge/by-thanhng224-brightgreen?logo=github&style=social)](https://github.com/ThanhNg224)&nbsp;&nbsp;
![License](https://img.shields.io/github/license/its-me-debk007/kotlin-android-mvvm-template.svg?style=social)

# Kotlin Android MVVM Template

A simple+lightweight template for a Jetpack Compose app with Navigation, Retrofit, Dagger-Hilt fully setup for convenience, so you can **focus only on what's important!**

> ***"Every line of code should be written once, and only once." - Ron Jeffries***

## Features 🎨

- Project fully in `Jetpack Compose`
- Jetpack Compose Navigation
- Retrofit *(Fully setup with a **working** sample API Call)*
> [!NOTE]
> for API Call using **ktor**, switch to `ktor` branch
- MVVM Architecture
- Kotlin DSL
- `Gradle Version Catalog` for dependency management
- `detekt` for code smell analysis - configured for **Jetpack Compose**
- `Dependabot` *(with a weekly schedule)*
- `GitHub Actions` CI/CD pipeline to **run detekt lint checks** + **perform unit tests** + **build and upload apk**
- Coil *(for image loading)*


## Getting Started 👣

- Click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-499D95?style=for-the-badge)](https://github.com/new?template_name=kotlin-android-mvvm-template&template_owner=its-me-debk007) button to create a new repository from this template
- Few TODOs are added, such as changing *package name*, *server base url*, *api endpoints*, etc to guide through the project *(just type TODO after pressing SHIFT key twice in Android Studio, to search for the TODOs)*
- Enable Dependabot in repo settings *(for ref, a [30 sec video](https://www.youtube.com/watch?v=yvXKlDgiGHo))*


## Project Structure 🗺️📐🛠️

The project includes a single module **`app`**, with following packages:

- **`di`:** contains Dagger-Hilt Module
- **`network`:** includes api service containing api endpoints & type of request
- **`model`:** contains response data class from the server
- **`repository`:** contains repository interface & its implementation
- **`presentation`:** signifies the presentation layer


