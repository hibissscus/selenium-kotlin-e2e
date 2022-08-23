package e2e.pages.space

import e2e.model.PageTitles
import e2e.pages.NavigationPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement

class BlogPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val newArticle_ = ".XButtonStyles-button"
    }

    @FindBy(css = newArticle_)
    private lateinit var newArticle: WebElement

    override fun isOpened(s: String): BlogPage = apply {
        wait().until(textToBePresentInElement(sidebarHeader, PageTitles.BLOG.title))
        wait().until(textToBePresentInElement(newArticle, "New article"))
    }

}