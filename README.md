# ReleaseProbe

[![CircleCI](https://circleci.com/gh/ReactiveCircus/release-probe.svg?style=svg)](https://circleci.com/gh/ReactiveCircus/release-probe)
[![GitHub Actions status](https://github.com/ReactiveCircus/release-probe/workflows/Instrumented%20test%20workflow/badge.svg)](https://github.com/ReactiveCircus/release-probe/actions)
[![Android API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat-square&label=API&maxAge=300)](https://www.android.com/history/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square&maxAge=3600)](https://opensource.org/licenses/MIT)

An Android app for monitoring new releases of libraries and dependencies such as AndroidX, Firebase and other popular 3rd party libraries on GitHub. Get notified when your favourite library has a new release.

## Goals
The main goal of this project is to experiment with some of the latest technologies including, libraries, architecture, infrastructure, tooling, design patterns in Android development, and apply them in a non-trivial project. Therefore it's expected that the focus will be mostly on the technologies rather than adding new features. Current tech stack is documented below.

## Tech Stack

### Language
100% Kotlin.

TODO

### Architecture Guideline

TODO

### Core Libraries

TODO

### Microservices Architecture

TODO

### Project Dependency Graph

To get a visualization of the project's dependency graph ([Graphviz](https://brewinstall.org/Install-graphviz-on-Mac-with-Brew/) required):

```
./gradlew projectDependencyGraph
```

The generated project dependency graph is available at `./build/reports/dependency-graph/project.dot.png`.

### Testing

TODO

### Code Quality

In addition to Android Lint, [detekt](https://github.com/arturbosch/detekt) has been configured for Kotlin static analysis.

### Continuous Integration

We use **CircleCI** for CI/CD. We also have a separate workflow for running instrumented tests with **GitHub Actions**.

### Workflow

#### All pull requests and pushes to main

* **build (CircleCI)** - assembles the release APK and App Bundle
* **unit_tests (CircleCI)** - runs all unit tests
* **static_analysis (CircleCI)** - runs Android Lint and detekt
* **Run Instrumented Tests (GitHub Actions)** - runs instrumented tests on Emulators

#### On release branches only

* **deploy_to_play (CircleCI)** - deploys the release App Bundle to Google Play's internal test channel

### Docker Container
[reactivecircus/android-sdk](https://hub.docker.com/r/reactivecircus/android-sdk/) is used for running CI jobs. Dockerfiles are available on [GitHub](https://github.com/reactivecircus/docker-android-images)

### Running Instrumented Tests with GitHub Actions

Running proper automated UI tests on CI remains a challenge especially when operating with free plans for side projects.

Therefore we are running our instrumented tests with a custom GitHub Action - [Android Emulator Runner](https://github.com/ReactiveCircus/android-emulator-runner) which installs, configures and runs Android Emulators on **macOS** virtual machines with hardware acceleration enabled. 

## Building

### Bugsnag

A Bugsnag API key is required. Place the following in `~/.gradle/gradle.properties`:
```
RELEASE_PROBE_BUGSNAG_API_KEY=<key>
```

### APK and Bundle

The following will generate the unsigned APK and AAB (App Bundle) files.

`./gradlew assemble bundle`

Signed APK and AAB will be generated when the release keystore and signing credentials are provided, which happens on CI builds.

## TODO
