package e2e.space.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ProfilePage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val showMore_ = "[title='Show more'][role='button']"
    }

    @FindBy(css = showMore_)
    private lateinit var showMore: WebElement

    override fun href(): String {
        return "/m/"
    }

    override fun title(): String {
        return ""
    }

    override fun opened(s: String): ProfilePage = apply {
        urlContains(href())
        textToBe(By.cssSelector(heading_), s)
    }

    fun checkAvailability(availability: String): ProfilePage = apply {
        retry(1) {
            driver().navigate().refresh()
            loaded()
            if (isVisible(showMore)) click(showMore)
            presence(By.xpath("//span[contains(text(),'$availability')]"))
        }
    }

}