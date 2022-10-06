package e2e.space.test.team

import e2e.space.model.User
import e2e.space.pages.TeamPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["team"])
class TeamTest : LoginTestBase() {

    @Test
    fun `can navigate to team page`() {
        login(User.QUEEN)
            .goTo(TeamPage(driver))
    }

    @Test(dependsOnMethods = ["can navigate to team page"])
    fun `can see team members`() {
        TeamPage(driver)
            .selectTab("Members")
            .apply {
                User.values().forEach {
                    selectMember(it.uiName)
                }
            }
    }

    @Test(dependsOnMethods = ["can navigate to team page"])
    fun `change member availability`() {
        TeamPage(driver)
            .changeAvailability()
            .selectTab("Members")
            .selectMember(User.QUEEN.uiName)
    }
}