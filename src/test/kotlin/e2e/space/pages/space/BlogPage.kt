package e2e.space.pages.space

import e2e.space.model.PageTitles
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