`Selenium` end-to-end test example for [Space](https://www.jetbrains.com/space/) [![e2e](https://github.com/hibissscus/selenium-kotlin-e2e/actions/workflows/e2e.yml/badge.svg)](https://github.com/hibissscus/selenium-kotlin-e2e/actions/workflows/e2e.yml)
=================================

Example of test results generated by 🌈 [ReportNG](https://github.com/hibissscus/reportng) with a help of
✅ [Testee](https://github.com/hibissscus/testee) framework:
<img width="1347" alt="image" src="https://user-images.githubusercontent.com/1389501/187028838-42a5cb04-6b76-4df7-aa89-5ce4db9acfa9.png">

![space_e2e](https://user-images.githubusercontent.com/1389501/189634353-a56388ef-0eb2-4170-b2df-37c6804252f4.gif)

## GitHub Actions end-to-end results

👀 zip-arhive https://github.com/hibissscus/selenium-kotlin-e2e/suites/8163370723/artifacts/354560845

## Getting Started

SSH clone URL: git@github.com:hibissscus/selenium-kotlin-e2e.git

HTTPS clone URL: https://github.com/hibissscus/selenium-kotlin-e2e.git

## How to run E2E tests

1. `gradle` is used for building this `selenium-kotlin-e2e` project
2. To run `E2E` tests locally we need to install `chromedriver` or `geckodriver`
    - `brew install chromedriver` (or `brew upgrade chromedriver`)
    - `brew install geckodriver` (or `brew upgrade geckodriver`)
3. Go to `selenium-kotlin-e2e/src/test/kotlin/e2e/test/space` and run any of the test via IDEA with `test` profile (
   ex.: [TodoTest](https://github.com/hibissscus/selenium-kotlin-e2e/blob/master/src/test/kotlin/e2e/test/space/task/TodoTest.kt))
   or via gradle:
   ``
   ./gradlew test --tests "e2e.space.test.navigation.NavigationTest"
   ``
   ``
   ./gradlew test --tests "e2e.space.test.login.UserTest"
   ``
   ``
   ./gradlew test --tests "e2e.space.test.team.TeamTest"
   ``
   the whole e2e test suite can be started via gradle locally:
   ``
   ./gradlew test
   ``
4. For `Selenium` dockerization use [docker-compose.yml](https://github.com/hibissscus/selenium-kotlin-e2e/blob/master/docker-compose.yml)
   e2e test suite can be started via gradle using docker:
   ``
   ./gradlew e2e
   ``

### Authors

© 2022 [Sergei Stepanov](https://github.com/hibissscus) (Initial idea, implementation & enhancement)
