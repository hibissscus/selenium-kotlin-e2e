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

fun Test.testNG(
    desc: String, suite: String, reportngTitle: String = "test",
    zip: Boolean = false, pushToSlack: Boolean = false, slackChannel: String = "test"
) {
    description = desc
    group = "verification"
    outputs.upToDateWhen { false }
    testLogging.showStandardStreams = true
    reports.html.required.set(false)
    reports.junitXml.required.set(false)
    useTestNG {
        suites(suite)
        useDefaultListeners = false
        listeners = setOf("testee.it.reportng.HTMLReporter")
        systemProperties = mapOf(
            "testee.it.reportng.title" to reportngTitle,
            "testee.it.reportng.zip" to zip,
            "testee.it.reportng.slack" to pushToSlack,
            "testee.it.reportng.slack.token" to "xoxb-570287064214-2443726642001-fFC8nApKmOqFt6JGAOFx69xb",
            "testee.it.reportng.slack.channel" to slackChannel
        )
    }
}

tasks {
    test {
        testNG("run entire space e2e test suite locally", "src/test/resources/local.xml", "e2e-space-local")
    }
}

tasks.register<Test>("docker") {
    testNG("run entire space e2e test suite in docker", "src/test/resources/docker.xml", "e2e-space-docker", true)
}

dockerCompose {
    useComposeFiles.add(
        if (OperatingSystem.current() != null
            && OperatingSystem.current().toString().contains("aarch64")
        ) "docker-compose-arm.yml"
        else "docker-compose.yml"
    )
    stopContainers.set(false)
    isRequiredBy(tasks.getByName("docker"))
}