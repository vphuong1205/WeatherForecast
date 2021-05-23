# WeatherForecast
Android app to retrieve weather information based on searching city name

## Architecture
### Approach
Modularized code-base approach with Single-Activity Architecture with Navigation Component for benefits:

- Better separation of concerns. Feature related classes life in different modules and can't be referenced without explicit module dependency.
- Features can be developed in parallel by different teams or developers
- Each feature can be developed in isolation, independently from other features
- Faster compile time

We can think about each feature as the equivalent of microservice or private library.

This is a diagram present dependencies between project modules (Gradle sub-projects)
![module_dependencies](https://github.com/vphuong1205/WeatherForecast/blob/master/misc/images/architecture_high_level.png?raw=true)

We have four kinds of modules in the application:

### `app module`
- This is the main module. 
- Navigation setup with Single-Activity Architecture
- It contains code that wires multiple modules together (dependency injection setup... etc.) 
- Fundamental application configuration (custom application class, etc.).

### `core-module`
- The infrastructure layer is the core of the building block of the entire project
- Hold the pure infrastructure without domain knowledge
- Module that some of the features could depend
- Example: `core-network`, `core-authentication`.


### `lib-module`
- Module that some of the features could depend
- Can be shared across the features
- Example: `lib-viewmodel`, `lib-utils`. 

### `feature-module`
- The most common type of module containing all code related to a given feature.
- `Clean architecture` is the "core architecture" of the application, so each `feature-module` contains own set of Clean architecture layers:
![module_dependencies](https://github.com/vphuong1205/WeatherForecast/blob/master/misc/images/feature_clean_architecture?raw=true)

Each feature module contains `deps` layer for dependency inject setup and 3 layers `data`, `domain`, `presentation` with distinct set of responsibilities.
- Not depend on other feature modules.
- Example: `feature-forecast`, `feature-settings` .

### Data flow

## Upcoming improvements
- Jetpack Compose for UI
- CI pipeline with GitHub Actions.
- Encrypt database with SQLCipher to protect rooted devices
- Migration to Dagger Hilt
- Migration to Flow
- Migration to Dynamic Feature modules

## Install guideline

### Android Studio

Android Studio -> File -> New -> From Version control -> Git
- Enter https://github.com/vphuong1205/WeatherForecast.git into URL field an press Clone button
- Let start sync and wait some minutes then you can install the app to devices
- ./gradlew testDebugUnitTest - to run unit tests

### Use signed APK:
- Quick install from [this signed release APK](https://github.com/vphuong1205/WeatherForecast/blob/master/app/release/app-release.apk)

## Project technologies 
* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
    * [Dagger 2](https://dagger.dev/) - dependency injection
* Modern Architecture
    * Clean Architecture (at feature module level)
    * Single activity architecture ( with[Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
    * MVVM + Usecase (presentation layer)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* Testing
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/)
* UI
    * [Material design](https://material.io/design)
    * Reactive UI
* Security
    * Certificate Pinning with OkHttp client
    * HTTPS to encrypt data transferring
    * Sensitive data in gradle.properties file. In real-live setup these keys could be provided\overriden by CI.
* Static analysis tools
    * [Detekt](https://github.com/arturbosch/detekt#with-gradle) - verify complexity look for and code smell
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

## Statement checklist
- [x] 1. The application is a simple Android application which is written by Java/Kotlin.
- [x] 2. The application is able to retrieve the weather information from OpenWeatherMaps
API.
- [x] 3. The application is able to allow user to input the searching term.
- [x] 4. The application is able to proceed searching with a condition of the search term length
must be from 3 characters or above.
- [x] 5. The application is able to render the searched results as a list of weather items.
- [x] 6. The application is able to support caching mechanism so as to prevent the app from
generating a bunch of API requests.
- [x] 7. The application is able to manage caching mechanism & lifecycle.
- [x] 8. The application is able to handle failures.
- [x] 9. The application is able to support the disability to scale large text for who can't see the
text clearly.
- [x] 10.The application is able to support the disability to read out the text using VoiceOver
controls.

## Requirements checklist done
- [x] 1. Programming language: Kotlin.
- [x] 2. Design app's architecture Clean Architecture(MVVM + UseCase)
- [x] 3. Apply LiveData mechanism
- [x] 4. UI should be looks like in attachment(Inluded some improvements).
- [x] 5. Write UnitTests
- [x] 6. Acceptance Tests
- [x] 7. Exception handling
- [x] 8. Caching handling
- [x] 9. Secure Android app from:
- [ ] a. Decompile APK
- [x] b. Rooted device
- [x] c. Data transmission via network
- [ ] d. Encryption for sensitive information
- [x] 10.Accessibility for Disability Supports:
  - [x] a. Talkback: Use a screen reader.
  - [x] b. Scaling Text: Display size and font size: To change the size of items on your screen,
adjust the display size or font size.
- [ ] 11.Entity relationship diagram for the database and solution diagrams for the
components, infrastructure design if any
- [x] 12.Readme file includes:
  - [ ] a. Brief explanation for the software development principles, patterns & practices being
applied
  - [ ] b. Brief explanation for the code folder structure and the key Java/Kotlin libraries and
frameworks being used
  - [ ] c. All the required steps in order to get the application run on local computer
  - [x] d. Checklist of items the candidate has done.
