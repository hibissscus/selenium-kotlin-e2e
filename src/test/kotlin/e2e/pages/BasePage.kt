package e2e.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import testee.it.e2e.core.pages.AbstractPage
import testee.it.e2e.core.pages.WaitForSeconds
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Main [BasePage] which implement [AbstractPage] interface.
 * All tests pages should extend this [BasePage]
 * Those methods can be overridden
 */
abstract class BasePage(driver: WebDriver) : AbstractPage(driver) {

    protected val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    protected val localDate: LocalDate = LocalDate.now()
    protected val localTime: LocalTime = LocalTime.now()

    override fun waitMin(): Long {
        return 3
    }

    abstract fun isOpened(s: String = ""): BasePage

    open fun isLoaded(): BasePage = apply {
        waitForLoaded()
    }

    companion object {
        /**
         * Waiting for amount of seconds on the [WaitForSeconds]
         *
         * @param timeoutInSeconds timeout in seconds for wait
         */
        fun <P : BasePage> P.waitForSeconds(timeoutInSeconds: Int): P {
            return waitForSeconds(timeoutInSeconds)
        }

        /**
         * Navigate to [url] of for this [BasePage]
         * Check that on [BasePage] are no loading process
         */
        fun <P : BasePage> P.open(url: String): P {
            driver().navigate().to(url)
            return view(this)
        }

        /**
         * Navigate to new [BasePage] by [url]
         * Check that on [BasePage] are no loading process
         */
        fun <P : BasePage, T : BasePage> P.open(page: T, url: String): T {
            driver().navigate().to(url)
            return view(page)
        }

        /**
         * Click on [element] and go to specific [BasePage]
         * Check that on [BasePage] are no loading process
         */
        fun <P : BasePage, T : BasePage> P.view(element: WebElement, page: T, title: String = ""): T {
            click(element)
            page.isLoaded().isOpened(title)
            return page
        }

        /**
         * View specific [BasePage] and check that on [BasePage] are no loading process
         */
        fun <P : BasePage, T : BasePage> P.view(page: T, title: String = ""): T {
            page.isLoaded().isOpened(title)
            return page
        }
    }

    fun navigate(url: String) {
        driver().navigate().to(url)
    }

    fun urlContains(url: String) {
        wait().until(ExpectedConditions.urlContains(url))
    }

    fun titleIs(title: String) {
        wait().until(ExpectedConditions.titleIs(title))
    }
}