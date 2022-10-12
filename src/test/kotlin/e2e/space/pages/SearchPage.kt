package e2e.space.pages

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

    override fun opened(s: String): SearchPage = apply {
        visible(searchInput)
    }

    fun close(): SearchPage = apply {
        searchInput.sendKeys(Keys.ESCAPE)
    }
}