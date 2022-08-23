package e2e.pages.google

import e2e.pages.BasePage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class GooglePage(driver: WebDriver) : BasePage(driver) {

    @FindBy(css = "input[name='q']")
    private lateinit var search: WebElement

    @FindBy(xpath = "//*[contains(text(), 'Accept all')]/parent::button")
    private lateinit var acceptAll: WebElement

    override fun isOpened(s: String): GooglePage = apply {
        visible(acceptAll)
    }

    fun acceptAll(): GooglePage = apply {
        click(acceptAll)
    }

    fun search(query: String): GooglePage = apply {
        sendText(search, query)
    }

}