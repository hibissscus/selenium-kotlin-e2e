package e2e.space.pages

import e2e.space.model.User
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val title_ = "a[href='#'][role='button']"
        const val userAvatar_ = ".XAvatarStyles-wrapper"
    }

    @FindBy(css = title_)
    private lateinit var title: WebElement

    @FindBy(css = userAvatar_)
    private lateinit var userAvatar: WebElement

    override fun opened(s: String): HomePage = apply {
        titleIs("e2e — Space")
    }

    override fun title(): String {
        return ""
    }

    fun isUserNamePresent(user: User): HomePage = apply {
        isUserNamePresent(user.uiName)
    }

    fun isUserNamePresent(name: String): HomePage = apply {
        attributeContains(userAvatar, "aria-label", name)
    }
}