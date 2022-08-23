# selenium-kotlin-e2e

Selenium end to end tests for Space.

## Getting Started

Download links:

SSH clone URL: ssh://git@git.jetbrains.space/e2e/selenium-kotlin-e2e.git

HTTPS clone URL: https://git.jetbrains.space/e2e/selenium-kotlin-e2e.git



These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


## How to run E2E tests

1. `gradle` is used for building this `E2E` project
2. To run `E2E` tests locally we need to install `chromedriver` or `geckodriver`
    - `brew cask install chromedriver` (or `brew upgrade --cask chromedriver`)
    - `brew install geckodriver` (or `brew upgrade geckodriver`)
3. Go to `selenium-kotlin-e2e/src/test/kotlin/e2e/test/space` and run any of the test via IDEA with `test` profile (ex.: [NavigationTest](https://github.com/hibissscus/selenium-kotlin-e2e/blob/master/src/test/kotlin/e2e/test/space/NavigationTest.kt))
4. For `Selenium` dockerization use [docker-compose.yml](https://github.com/hibissscus/selenium-kotlin-e2e/blob/master/docker-compose.yml)
    - `docker-compose up e2e`
    - `docker-compose down`

### Authors

© 2022 [Sergei Stepanov](https://github.com/hibissscus) (Initial idea, implementation & enhancement)
