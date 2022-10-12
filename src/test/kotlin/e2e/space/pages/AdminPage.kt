package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class AdminPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val menuSubtitle_ = ".CommonMenuStyles-menuSubtitle"
    }

    @FindBy(css = menuSubtitle_)
    private lateinit var menuSubtitle: WebElement

    override fun href(): String {
        return "/manage"
    }

    override fun title(): String {
        return PageTitles.ADMINISTRATION.title
    }

    override fun opened(s: String): AdminPage = apply {
        urlContains(href())
        textToBe(sidebarHeader, title())
        textToBe(By.cssSelector(menuSubtitle_), "Organization")
    }

}