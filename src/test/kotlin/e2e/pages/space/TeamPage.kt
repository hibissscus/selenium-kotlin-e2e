package e2e.pages.space

import e2e.model.PageTitles
import e2e.pages.NavigationPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement

class TeamPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val menuLogo_ = ".menu__logo"
    }

    @FindBy(css = menuLogo_)
    private lateinit var menuLogo: WebElement

    override fun isOpened(s: String): TeamPage = apply {
        wait().until(textToBePresentInElement(sidebarHeader, PageTitles.TEAMS.title))
    }

}