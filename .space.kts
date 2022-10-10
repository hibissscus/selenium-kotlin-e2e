/**
 * JetBrains Space Automation
 * see https://www.jetbrains.com/help/space/automation.html
 */

job("Build and run e2e tests using docker") {
    startOn {
        gitPush {}
    }

    failOn {
        testFailed { enabled = false }
        nonZeroExitCode { enabled = false }
        timeOut {
            runningTimeOutInMinutes = 10
        }
    }

    requirements {
        workerPool = WorkerPools.SPACE_CLOUD
    }

    host {
        shellScript {
            content = """
                echo Spin up selenium chrome nodes in docker...
                docker-compose up chrome
                echo Build and run e2e tests using docker...
                gradle docker 
                echo Uploading report of e2e test results...
                echo SOURCE_PATH=${'$'}JB_SPACE_WORK_DIR_PATH/build/reports/tests/docker/e2e/e2e.png
                echo TARGET_PATH=/${'$'}JB_SPACE_EXECUTION_NUMBER/
                echo REPO_URL=https://files.pkg.jetbrains.space/e2e/p/e2e/e2e-test-reports
                curl -i -H "Authorization: Bearer ${'$'}JB_SPACE_CLIENT_TOKEN" -F file=@"${'$'}SOURCE_PATH" ${'$'}REPO_URL/${'$'}TARGET_PATH
                echo Switch off docker containers...
                docker-compose down --remove-orphans
            """
        }
    }
}
