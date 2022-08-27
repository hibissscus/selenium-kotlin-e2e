package e2e.pages.space

import e2e.model.User
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.attributeContains

class HomePage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val title_ = "a[href='#'][role='button']"
        const val userLogo_ = ".ApplicationStyles-logo"
    }

    @FindBy(css = title_)
    private lateinit var title: WebElement

    @FindBy(css = userLogo_)
    private lateinit var userLogo: WebElement

    override fun isOpened(s: String): HomePage = apply {
        titleIs("e2e — Space")
        textToBe(title, "Personal navigation")
    }

    fun isUserNamePresent(user: User): HomePage = apply {
        wait().until(attributeContains(userLogo, "aria-label", user.uiName))
    }

    fun isUserNamePresent(name: String): HomePage = apply {
        wait().until(attributeContains(userLogo, "aria-label", name))
    }
}