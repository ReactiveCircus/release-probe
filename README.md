# ReleaseProbe

[![CircleCI](https://circleci.com/gh/ReactiveCircus/release-probe.svg?style=svg)](https://circleci.com/gh/ReactiveCircus/release-probe) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/c3adc2310a3f4872a08567d32da829da)](https://www.codacy.com/app/ReactiveCircus/release-probe?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ReactiveCircus/release-probe&amp;utm_campaign=Badge_Grade) [![Android API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat-square&label=API&maxAge=300)](https://www.android.com/history/) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square&maxAge=3600)](https://opensource.org/licenses/MIT)

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

We use CircleCI 2.0 for CI/CD.

### Workflow

#### All pull requests

* **build** - assembles the release APK and App Bundle
* **unit_tests** - runs all unit tests
* **static_analysis** - runs Android Lint and detekt

#### On release branches only

* **deploy_to_play** - deploys the release App Bundle to Google Play's internal test channel

### Docker Container
[reactivecircus/android-sdk](https://hub.docker.com/r/reactivecircus/android-sdk/) is used for running CI jobs. Dockerfiles are available on [GitHub](https://github.com/reactivecircus/docker-android-images)

### A Note on Running Instrumented Tests

Running proper automated UI tests on CI remains a challenge especially when operating with free plans for side projects. We are not running UI tests on CI at this point for lack of feasible options:

* CircleCI (and most other hosted CI providers) does not yet support running x86 emulators on their host machines (even if they do, free plans usually wouldn't provide enough RAM for running emulators in the containers).
* Firebase Test Lab only allows 10 tests/day on virtual device and 5 tests/day on physical device with the Spark (free) Plan.

Apparently Google / Robolectric are working on support for running Espresso tests off-device as part of [Nitrogen](https://youtu.be/wYMIadv9iF8?t=1843), which might change the landscape quite a bit depending on what we value in doing automated UI testing.

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
