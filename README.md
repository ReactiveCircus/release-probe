ReleaseProbe
============

An Android app for monitoring new releases of libraries and dependencies such as AndroidX, Firebase and other popular 3rd party libraries on GitHub. Get notified when your favorite library has a new release.

Goals
-----
The main goal of this project is to experiment with some of the latest technologies including, libraries, architecture, infrastructure, tooling, design patterns in Android development, and apply them in a non-trivial project. Therefore it's expected that the focus will be mostly on the technologies rather than adding new features. Current tech stack is documented below.

Tech Stack
----------

### Language
100% Kotlin

TODO

### Architecture Guideline

TODO

Microservices Architecture
--------------------------

TODO

Testing
-------

TODO

Continuous Integration
----------------------

TODO

Building
--------
### Bugsnag

A Bugsnag API key is required. Place the following in `~/.gradle/gradle.properties`:
```
RELEASE_PROBE_BUGSNAG_API_KEY=<key>
```

### APK and Bundle

The following will generate the unsigned APK and AAB (App Bundle) files.

`./gradlew assemble bundle`

Signed APK and AAB will be generated when the release keystore and signing credentials are provided, which happens on CI builds.

TODO
----
