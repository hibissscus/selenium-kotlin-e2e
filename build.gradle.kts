plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version ("1.7.20")
}

group = "ys-e2e"
version = "1.0.2"
description = "1.0.2"

repositories {
    mavenCentral()
    mavenLocal()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.4.0")
    // testng
    implementation("org.testng:testng:7.6.1")
    // testee + reportng
    implementation("com.github.hibissscus:testee:1.6.4")
}

tasks {
    test {
        description = "run e2e test locally"
        group = "verification"
        useTestNG {
            suites("src/test/resources/local.xml")
            useDefaultListeners = true
            listeners = setOf("testee.it.reportng.HTMLReporter")
            systemProperties = mapOf(
                "testee.it.reportng.title" to "e2e-space",
                "testee.it.reportng.slack" to "true",
                "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
                "testee.it.reportng.slack.channel" to "test"
            )
        }
    }
}

tasks.register<Test>("e2e") {
    description = "run entire e2e test suite"
    group = "verification"
    useTestNG {
        suites("src/test/resources/e2e.xml")
        useDefaultListeners = false
        listeners = setOf("testee.it.reportng.HTMLReporterRuntime", "testee.it.reportng.HTMLReporter")
        systemProperties = mapOf(
            "testee.it.reportng.title" to "e2e-space",
            "testee.it.reportng.slack" to "true",
            "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
            "testee.it.reportng.slack.channel" to "test"
        )
    }
}