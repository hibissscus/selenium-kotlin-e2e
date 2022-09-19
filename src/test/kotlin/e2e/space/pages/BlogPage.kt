package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class BlogPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val newArticle_ = ".XButtonStyles-button"
    }

    @FindBy(css = newArticle_)
    private lateinit var newArticle: WebElement

    override fun opened(s: String): BlogPage = apply {
        textToBe(sidebarHeader, PageTitles.BLOG.title)
        textToBe(newArticle, "New article")
    }

}