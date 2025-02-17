# NBA App - Showcase of Clean Architecture and Modern Android Skills

This application is a demonstration of my skills in developing modern Android applications, with an emphasis on clean architecture, testing, and the use of the latest technologies. The application displays information about players and teams from the NBA league.

## Key Features

*   **Player List View:** Users can browse a paged list of NBA players.
*   **Player Detail:** Display detailed information about a specific player
*   **Team Detail:** Display detailed information about a specific team.

## Technologies and Libraries Used

*   **Kotlin:** The main programming language.
*   **Jetpack Compose:** A modern UI toolkit for declarative UI development.
*   **Clean Architecture:** Layer separation for better code organization and testability.
*   **Hilt:** Dependency injection framework to simplify dependency management.
*   **Coroutines:** For asynchronous operations and thread management.
*   **Flow:** For reactive programming and working with data streams.
*   **Retrofit:** For network communication with the API.
*   **OkHttp:** For efficient HTTP requests.
*   **Mockito:** For unit testing.
*   **JUnit:** Testing framework.

## Architecture

The application is designed with an emphasis on **Clean Architecture** principles, which ensures:

*   **Separation of Concerns:** Each layer has a clearly defined responsibility.
*   **Testability:** Each layer is easily testable.
*   **Maintainability:** The code is easily extensible and modifiable.
*   **Framework Independence:** The domain layer is independent of the Android framework.

The application is divided into the following modules:

*   **`app`:** The application module that contains navigation and launches the application.
*   **`presentation`:** The module for data presentation and UI logic (ViewModels, UI states).
*   **`domain`:** The module with business logic (Use Cases, Entities).
*   **`data`:** The module for data handling (Repositories, Data Sources).
*   **`core-ui`:** The module for shared UI theme.

## Why is the Application Built This Way?

*   **Clean Architecture:** Using Clean Architecture allows for the separation of business logic from the UI and framework. This leads to better testability, maintainability, and extensibility of the code.
*   **Jetpack Compose:** Using Jetpack Compose enables declarative UI development, resulting in simpler and more readable code.
*   **Hilt:** Using Hilt simplifies dependency management and reduces the amount of boilerplate code.
*   **Coroutines and Flow:** Using Coroutines and Flow enables efficient work with asynchronous operations and data streams.
*   **Testing:** The application is designed with an emphasis on testing. All layers are testable using unit tests.

## Future Improvements

*   **Local Database (Room):** Implementing a local database for offline mode and data caching.
*   **More Tests:** Expanding tests to cover more scenarios and coverage.
*   **Instrumented tests:** Adding instrumented tests for testing the UI.

## Running the Application

1.  Clone the repository: `git clone https://github.com/petr-vostinak/nba.git`
2.  Open the project in Android Studio.
3.  Get your API key from [BallDon'tLie](https://www.balldontlie.io/).
4.  Create a `gradle.properties` file in the root directory of the project and add `API_KEY=your_api_key` to it.
5.  Run the application on an emulator or device.

## Contact

Petr Vostiňák - petr@vostinak.cz - [LinkedIn](https://www.linkedin.com/in/petrvostinak/)

---

## Note

This application is only a demonstration of my skills and is not intended for production use.