package e2e.space.test.navigation

import e2e.space.model.User
import e2e.space.pages.AdminPage
import e2e.space.pages.BlogPage
import e2e.space.pages.HomePage
import e2e.space.pages.ProjectPage
import e2e.space.pages.TeamPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["navigation"])
class NavigationTest : LoginTestBase() {

    @Test
    fun `navigation quick access pages`() {
        login(User.KING)
            .switchAllQuickAccessPages(false)
            .switchAllQuickAccessPages(true)
            .switchAllQuickAccessPages(false)
            .switchOnQuickAccessPage(BlogPage(driver))
            .switchOnQuickAccessPage(ProjectPage(driver))
            .switchOnQuickAccessPage(TeamPage(driver))
            .switchOnQuickAccessPage(AdminPage(driver))

    }

    @Test(dependsOnMethods = ["navigation quick access pages"])
    fun `can switch between pages`() {
        HomePage(driver)
            .goTo(BlogPage(driver))
            .goTo(ProjectPage(driver))
            .goTo(TeamPage(driver))
            .goTo(AdminPage(driver))
    }
}