/**
 * JetBrains Space Automation
 * see https://www.jetbrains.com/help/space/automation.html
 */

job("Build and run e2e tests in docker") {
    container(image = "ubuntu:latest") {
        shellScript {
            content = "./gradlew docker"
        }
    }
}
