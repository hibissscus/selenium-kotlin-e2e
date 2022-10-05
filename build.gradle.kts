import org.gradle.internal.os.OperatingSystem

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version ("1.7.20")
    id("com.avast.gradle.docker-compose") version ("0.16.9")
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
    implementation("org.seleniumhq.selenium:selenium-java:4.5.0")
    // testng
    implementation("org.testng:testng:7.6.1")
    // testee + reportng
    implementation("com.github.hibissscus:testee:1.6.5")
}

tasks {
    test {
        description = "run entire e2e test suite locally"
        group = "verification"
        outputs.upToDateWhen { false }
        testLogging.showStandardStreams = true
        reports.html.required.set(false)
        reports.junitXml.required.set(false)
        useTestNG {
            suites("src/test/resources/local.xml")
            useDefaultListeners = false
            listeners = setOf("testee.it.reportng.HTMLReporter")
            systemProperties = mapOf(
                "testee.it.reportng.title" to "e2e-space",
                "testee.it.reportng.slack" to "false",
                "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
                "testee.it.reportng.slack.channel" to "test"
            )
        }
    }
}

tasks.register<Test>("docker") {
    description = "run entire e2e test suite in docker"
    group = "verification"
    outputs.upToDateWhen { false }
    testLogging.showStandardStreams = true
    reports.html.required.set(false)
    reports.junitXml.required.set(false)
    useTestNG {
        suites("src/test/resources/e2e.xml")
        useDefaultListeners = false
        listeners = setOf("testee.it.reportng.HTMLReporter")
        systemProperties = mapOf(
            "testee.it.reportng.title" to "e2e-space",
            "testee.it.reportng.slack" to "false",
            "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
            "testee.it.reportng.slack.channel" to "test"
        )
    }
}

dockerCompose {
    useComposeFiles.add(
        if (OperatingSystem.current().toString().contains("aarch64")) "docker-compose-m1.yml"
        else "docker-compose.yml"
    )
    isRequiredBy(tasks.getByName("docker"))
}