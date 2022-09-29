package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class TeamPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val menuLogo_ = ".menu__logo"
    }

    @FindBy(css = menuLogo_)
    private lateinit var menuLogo: WebElement

    override fun href(): String {
        return "/team"
    }

    override fun name(): String {
        return PageTitles.TEAMS.title
    }

    override fun opened(s: String): TeamPage = apply {
        urlContains(href())
        textToBe(sidebarHeader, name())
    }

}