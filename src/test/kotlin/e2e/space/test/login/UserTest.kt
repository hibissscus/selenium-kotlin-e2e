package e2e.space.test.login

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.space.HomePage
import e2e.space.pages.space.LoginPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class UserTest : TestBase(url = Url.SPACE.url) {

    @Test
    fun `01 login with king`() {
        LoginPage(driver)
            .isOpened("e2e")
            .login(User.KING)
            .view(HomePage(driver))
            .isUserNamePresent(User.KING)
            .logout()
    }

    @Test
    fun `02 login with queen`() {
        LoginPage(driver)
            .login(User.QUEEN)
            .view(HomePage(driver))
            .isUserNamePresent(User.QUEEN)
            .logout()
    }

    @Test
    fun `03 login with rook`() {
        LoginPage(driver)
            .login(User.ROOK)
            .view(HomePage(driver))
            .isUserNamePresent(User.ROOK)
            .logout()
    }
}