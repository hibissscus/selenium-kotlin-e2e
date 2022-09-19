package e2e.space.test.login

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.HomePage
import e2e.space.pages.LoginPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class LoginTest : TestBase(url = Url.SPACE.url) {

    fun login(user: User): LoginPage {
        return LoginPage(driver)
            .opened("e2e")
            .login(user)
            .view(HomePage(driver))
            .logout()
    }

    @Test
    fun `01 login with king`() {
        login(User.KING)
    }

    @Test
    fun `02 login with queen`() {
        login(User.QUEEN)
    }

    @Test
    fun `03 login with rook`() {
        login(User.ROOK)
    }
}