package e2e.space.test.login

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.BasePage.Companion.waitForSeconds
import e2e.space.pages.HomePage
import e2e.space.pages.LoginPage
import testee.it.e2e.core.test.TestBase

open class LoginTestBase : TestBase(url = Url.SPACE.url) {

    fun login(user: User): HomePage {
        return LoginPage(driver)
            .opened("e2e")
            .login(user)
            .waitForSeconds(2)
            .view(HomePage(driver))
            .isUserNamePresent(user)
    }
}