package e2e.space.test.navigation

import e2e.space.model.User
import e2e.space.pages.AdminPage
import e2e.space.pages.BlogPage
import e2e.space.pages.HomePage
import e2e.space.pages.ProjectsPage
import e2e.space.pages.SearchPage
import e2e.space.pages.TeamPage
import e2e.space.pages.TodoPage
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
            .switchOnQuickAccessPage(SearchPage(driver))
            .switchOnQuickAccessPage(TodoPage(driver))
            .switchOnQuickAccessPage(ProjectsPage(driver))
            .switchOnQuickAccessPage(BlogPage(driver))
            .switchOnQuickAccessPage(TeamPage(driver))
            .switchOnQuickAccessPage(AdminPage(driver))

    }

    @Test(dependsOnMethods = ["navigation quick access pages"])
    fun `can switch between pages`() {
        HomePage(driver)
            .goTo(TodoPage(driver))
            .goTo(ProjectsPage(driver))
            .goTo(BlogPage(driver))
            .goTo(TeamPage(driver))
            .goTo(AdminPage(driver))
    }
}