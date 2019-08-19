# ReleaseProbe

[![CircleCI](https://circleci.com/gh/ReactiveCircus/release-probe.svg?style=svg)](https://circleci.com/gh/ReactiveCircus/release-probe) [![Build Status](https://app.bitrise.io/app/4ffe96455db1c357/status.svg?token=APH0_JyYDtw37bY1fgKnYg&branch=master)](https://app.bitrise.io/app/4ffe96455db1c357) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/c3adc2310a3f4872a08567d32da829da)](https://www.codacy.com/app/ReactiveCircus/release-probe?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ReactiveCircus/release-probe&amp;utm_campaign=Badge_Grade) [![Android API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat-square&label=API&maxAge=300)](https://www.android.com/history/) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square&maxAge=3600)](https://opensource.org/licenses/MIT)

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

We also use [Codacy](https://app.codacy.com/project/reactivecircus/release-probe/dashboard) for automatic PR quality review.

### Continuous Integration

We use **CircleCI** for CI/CD. We also have a separate pipeline for running instrumented tests on **Bitrise**.

### Workflow

#### All pull requests and pushes to master

* **build (CircleCI)** - assembles the release APK and App Bundle
* **unit_tests (CircleCI)** - runs all unit tests
* **static_analysis (CircleCI)** - runs Android Lint and detekt
* **Run Instrumented Tests (Bitrise)** - runs instrumented tests on a virtual device
* **PR Quality Review (Codacy)**

#### On release branches only

* **deploy_to_play (CircleCI)** - deploys the release App Bundle to Google Play's internal test channel

### Docker Container
[reactivecircus/android-sdk](https://hub.docker.com/r/reactivecircus/android-sdk/) is used for running CI jobs. Dockerfiles are available on [GitHub](https://github.com/reactivecircus/docker-android-images)

### Running Instrumented Tests on Bitrise

Running proper automated UI tests on CI remains a challenge especially when operating with free plans for side projects. We are using **Bitrise** to run all instrumented tests as one of the GitHub PR **checks**, as it's the only service that supports running x86 emulators with hardware acceleration (they offer 1 free container for open-source projects). Other solutions we've looked at:

* CircleCI (and most other hosted CI providers) does not yet support running x86 emulators on their host machines (even if they do, free plans usually wouldn't provide enough RAM for running emulators in the containers).
* Firebase Test Lab only allows 10 tests/day on virtual device and 5 tests/day on physical device with the Spark (free) Plan.
* [Robolectric 4.x](https://github.com/robolectric/robolectric) introduced support for running Espresso tests. While sharing test source between JVM and on-device tests mostly works, robolectric's shadowed environment is still too unstable / immature to be considered useful as they often have very different behaviors than when running on an emulator or real device. 

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
