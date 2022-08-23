package e2e.test.space

import e2e.model.Url
import e2e.model.User
import e2e.pages.BasePage.Companion.view
import e2e.pages.space.AdminPage
import e2e.pages.space.BlogPage
import e2e.pages.space.HomePage
import e2e.pages.space.LoginPage
import e2e.pages.space.ProjectPage
import e2e.pages.space.TeamPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class NavigationTest : TestBase(url = Url.SPACE.url) {

    @Test
    fun `01 login test`() {
        LoginPage(driver)
            .isOpened("e2e")
            .login(User.KING)
            .view(HomePage(driver))
    }

    @Test
    fun `02 can switch between pages`() {
        HomePage(driver)
            .goTo(BlogPage(driver))
            .goTo(ProjectPage(driver))
            .goTo(TeamPage(driver))
            .goTo(AdminPage(driver))
    }
}