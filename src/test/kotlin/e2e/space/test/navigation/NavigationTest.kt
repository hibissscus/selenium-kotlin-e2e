package e2e.space.test.navigation

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.AdminPage
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.BlogPage
import e2e.space.pages.HomePage
import e2e.space.pages.LoginPage
import e2e.space.pages.ProjectPage
import e2e.space.pages.TeamPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class NavigationTest : TestBase(url = Url.SPACE.url) {

    @Test
    fun `01 login test`() {
        LoginPage(driver)
            .opened("e2e")
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