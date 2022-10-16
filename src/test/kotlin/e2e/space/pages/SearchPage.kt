package e2e.space.pages

import e2e.space.model.PageTitles
import e2e.space.pages.create.AbsencePage
import e2e.space.pages.create.BlogPostPage
import e2e.space.pages.create.ChannelPage
import e2e.space.pages.create.CodeReviewPage
import e2e.space.pages.create.DocumentPage
import e2e.space.pages.create.MergeRequestPage
import e2e.space.pages.create.ProjectPage
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SearchPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val searchInput_ = "input[placeholder='Go to anything'], input[placeholder='Type to search']"
        const val listitem_ = "[@role='listitem']"
    }

    @FindBy(css = searchInput_)
    private lateinit var searchInput: WebElement

    override fun title(): String {
        return PageTitles.SEARCH.title
    }

    private fun selectListItem(listItemText: String) {
        visible(searchInput)
        sendText(searchInput, listItemText)
        loaded()
        clickable(By.xpath("//*[contains(text(),'Search everywhere') and ancestor-or-self::*$listitem_]"))
        // click via javascript, due to Selenium issue with overlapping element exception "Element is not clickable at point"
        (driver() as JavascriptExecutor).executeScript(
            "arguments[0].click();", clickable(By.xpath("//*[contains(text(),'$listItemText') and ancestor-or-self::*$listitem_]"))
        )
    }

    override fun <T : BasePage> goToPage(element: WebElement, page: T, title: String): T = page.apply {
        selectListItem(page.title())
        view(page)
    }

    override fun <T : BasePage> goTo(page: T): T = page.apply {
        when (page) {
            is AbsencePage -> goToPage(searchInput, page)
            is BlogPostPage -> goToPage(searchInput, page)
            is ChannelPage -> goToPage(searchInput, page)
            is CodeReviewPage -> goToPage(searchInput, page)
            is DocumentPage -> goToPage(searchInput, page)
            is MergeRequestPage -> goToPage(searchInput, page)
            is ProjectPage -> goToPage(searchInput, page)
        }
    }

    override fun opened(s: String): SearchPage = apply {
        visible(searchInput)
    }

    fun close(): SearchPage = apply {
        searchInput.sendKeys(Keys.ESCAPE)
    }
}