# WeatherForecast
Android app to retrieve weather information based on searching city name

## Architecture
Android Single-Activity Architecture with Navigation Component

Feature related code is placed inside one of the feature-module. We can think about each feature as the equivalent of microservice or private library.

The modularized code-base approach provides benefit:
- better separation of concerns. Feature related classes life in different modules and can't be referenced without explicit module dependency.
- Features can be developed in parallel by different teams or developers
- Each feature can be developed in isolation, independently from other features
- Faster compile time

We have three kinds of modules in the application:
### app module: 
- This is the main module. 
- Navigation setup with Single-Activity Architecture
- It contains code that wires multiple modules together (dependency injection setup... etc.) 
- Fundamental application configuration (custom application class, etc.).

### Core-modules
- Modules that some of the features could depend on
- This is helpful for reuse code only between few feature modules


## Install Application

### Android Studio

Android Studio -> File -> New -> From Version control -> Git
- Enter https://github.com/vphuong1205/WeatherForecast.git into URL field an press Clone button
- Let start sync and wait some minutes then you can install the app to devices
- ./gradlew testDebugUnitTest - to run unit tests

### Use signed APK:
- Quick install from [this signed release APK](https://github.com/vphuong1205/WeatherForecast/blob/master/app/release/app-release.apk)


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
