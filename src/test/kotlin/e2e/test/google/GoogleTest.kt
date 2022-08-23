package e2e.test.google

import e2e.pages.google.GooglePage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class GoogleTest : TestBase(url = "https://google.com") {

    @Test
    fun `01 google test`() {
        GooglePage(driver)
            .isOpened()
            .acceptAll()
            .search("Google")
            .apply {
                println("Page title is: ${driver.title}")
            }
    }
}