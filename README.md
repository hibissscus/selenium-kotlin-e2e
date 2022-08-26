# Selenium-Kotlin-e2e

Selenium end-to-end tests for [Space](https://www.jetbrains.com/space/) done with a help of [testee](https://github.com/hibissscus/testee) framework.

Example of test results generated by [reportNG](https://github.com/hibissscus/reportng):
<img width="1350" alt="image" src="https://user-images.githubusercontent.com/1389501/186954505-6f1a676b-39e9-44db-a435-c20ead12089c.png">


## Getting Started

Download links:

SSH clone URL: git@github.com:hibissscus/selenium-kotlin-e2e.git

HTTPS clone URL: https://github.com/hibissscus/selenium-kotlin-e2e.git



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
