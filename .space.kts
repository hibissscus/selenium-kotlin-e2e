/**
 * JetBrains Space Automation
 * see https://www.jetbrains.com/help/space/automation.html
 */

job("Build and run e2e tests in docker") {
    container(image = "gradle") {
        shellScript {
            content = "gradle docker"
        }
    }
}
