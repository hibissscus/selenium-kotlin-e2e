package e2e.space.pages

import e2e.space.model.User
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class LoginPage(driver: WebDriver) : BasePage(driver) {

    companion object Path {
        const val frame_ = ".SignInDialogStyles-viewport"
        const val realmName_ = ".PageStyles-realmName"
        const val username_ = "[data-test-id='devLoginInput']"
        const val password_ = "[data-test-id ='devPasswordInput']"
        const val submit_ = "input[type ='submit']"
    }

    @FindBy(css = frame_)
    private lateinit var frame: WebElement

    @FindBy(css = realmName_)
    private lateinit var realmName: WebElement

    @FindBy(css = username_)
    private lateinit var username: WebElement

    @FindBy(css = password_)
    private lateinit var password: WebElement

    @FindBy(css = submit_)
    private lateinit var submit: WebElement

    override fun href(): String {
        return "/sign-in"
    }

    override fun opened(s: String): LoginPage = apply {
        titleIs("Sign In — Space")
        if (isPresent(By.cssSelector(frame_))) {
            frameToBeAvailableAndSwitchToIt(frame)
        }
        textToBe(realmName, s)
    }

    private fun login(usernameText: String, passwordText: String): LoginPage = apply {
        sendText(username, usernameText)
        sendText(password, passwordText)
        click(submit)
    }

    fun login(user: User): LoginPage = apply {
        login(user.username, user.password)
    }

}