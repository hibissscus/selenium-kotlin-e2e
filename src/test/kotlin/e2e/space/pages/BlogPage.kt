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

    override fun href(): String {
        return "/blog"
    }

    override fun name(): String {
        return PageTitles.BLOG.title
    }

    override fun opened(s: String): BlogPage = apply {
        urlContains(href())
        textToBe(sidebarHeader, name())
        textToBe(newArticle, "New article")
    }

}