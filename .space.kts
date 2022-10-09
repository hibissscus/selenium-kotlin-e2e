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
//        shellScript {
//            content = """
//                ./gradlew docker
//            """
//        }
        shellScript {
            content = """
                echo General:
                echo JB_SPACE_API_URL ${'$'}JB_SPACE_API_URL
                echo Authentication:
                echo JB_SPACE_CLIENT_TOKEN ${'$'}JB_SPACE_CLIENT_TOKEN
                echo JB_SPACE_CLIENT_ID ${'$'}JB_SPACE_CLIENT_ID
                echo Project settings:
                echo JB_SPACE_STEP_DATA_PATH ${'$'}JB_SPACE_STEP_DATA_PATH
                echo JB_SPACE_WORK_DIR_PATH ${'$'}JB_SPACE_WORK_DIR_PATH
                echo Automation:
                echo JB_SPACE_EXECUTION_NUMBER ${'$'}JB_SPACE_EXECUTION_NUMBER
                echo JB_SPACE_EXECUTION_ID ${'$'}JB_SPACE_EXECUTION_ID
                echo JB_SPACE_EXECUTION_URL ${'$'}JB_SPACE_EXECUTION_URL
                echo Self-hosted and cloud workers:
                echo SPACE_WORKER_SERVERURL ${'$'}SPACE_WORKER_SERVERURL
                echo SPACE_WORKER_TOKEN ${'$'}SPACE_WORKER_TOKEN
                echo SPACE_WORKER_DATADIR ${'$'}SPACE_WORKER_DATADIR
                echo SPACE_WORKER_HOSTNAME ${'$'}SPACE_WORKER_HOSTNAME
            """
        }
//        shellScript {
//            // SOURCE_PATH is path to the build artifact
//            // TARGET_PATH is the destination path in the file repository
//            // Note that each run of the build script creates a separate directory (name = build number)
//            content = """
//                echo Uploading report of e2e test results...
//                SOURCE_PATH=@tmp/jetbrains/space/automation/worker/data/steps-2EaVxe21Mqwv-2EaVxe21Mqwv-1/step-data-2EaVxe21Mqwv/work/selenium-kotlin-e2e/build/reports/tests/docker/e2e/index.html
//                TARGET_PATH=/${'$'}JB_SPACE_EXECUTION_NUMBER/
//                REPO_URL=https://files.pkg.jetbrains.space/e2e/p/e2e/e2e-test-reports
//                curl -i -H "Authorization: Bearer ${'$'}JB_SPACE_CLIENT_TOKEN" -F file=@"${'$'}SOURCE_PATH" ${'$'}REPO_URL/${'$'}TARGET_PATH
//            """
//        }
    }
}
