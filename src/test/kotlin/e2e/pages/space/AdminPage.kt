package e2e.pages.space

import e2e.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement
import org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated

class AdminPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val menuSubtitle_ = ".CommonMenuStyles-menuSubtitle"
    }

    @FindBy(css = menuSubtitle_)
    private lateinit var menuSubtitle: WebElement

    override fun isOpened(s: String): AdminPage = apply {
        wait().until(textToBePresentInElement(sidebarHeader, PageTitles.ADMINISTRATION.title))
        wait().until(textToBePresentInElementLocated(By.cssSelector(menuSubtitle_), "Organization"))
    }

}