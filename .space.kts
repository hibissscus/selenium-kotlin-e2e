/**
 * JetBrains Space Automation
 * see https://www.jetbrains.com/help/space/automation.html
 */

job("Hello from cloud worker") {
    // the job will run in a default regular ubuntu lts instance
    requirements {
        workerPool = WorkerPools.SPACE_CLOUD
    }

    host("Run echo") {
        shellScript {
            content = """
                docker-compose --version
            """
        }
    }
}
