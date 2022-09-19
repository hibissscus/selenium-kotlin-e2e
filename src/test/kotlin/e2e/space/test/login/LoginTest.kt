package e2e.space.test.login

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.space.HomePage
import e2e.space.pages.space.LoginPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class LoginTest : TestBase(url = Url.SPACE.url) {

    @Test
    fun `01 login test`() {
        LoginPage(driver)
            .isOpened("e2e")
            .login(User.KING)
            .view(HomePage(driver))
            .logout()
    }
}