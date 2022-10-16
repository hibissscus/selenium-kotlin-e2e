package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SearchPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val searchInput_ = "input[placeholder='Go to anything'], input[placeholder='Type to search']"
    }

    @FindBy(css = searchInput_)
    private lateinit var searchInput: WebElement

    override fun title(): String {
        return PageTitles.SEARCH.title
    }

    override fun <T : BasePage> goToCreatePage(element: WebElement, page: T, title: String): T = page.apply {
        selectListItem(page.title())
        view(page)
    }

    override fun opened(s: String): SearchPage = apply {
        visible(searchInput)
    }

    private fun selectListItem(listItemText: String) {
        visible(searchInput)
        sendText(searchInput, listItemText)
        loaded()
        clickable(By.xpath("//*[contains(text(),'Search everywhere') and ancestor-or-self::*$listitem_]"))
        // click via javascript, due to Selenium issue with overlapping element exception "Element is not clickable at point"
        (driver() as JavascriptExecutor).executeScript(
            "arguments[0].click();", clickable(By.xpath("//*[contains(text(),'$listItemText') and ancestor-or-self::*$listitem_]"))
        )
    }

    fun close(): SearchPage = apply {
        searchInput.sendKeys(Keys.ESCAPE)
    }
}