package e2e.space.test.team

import e2e.space.model.User
import e2e.space.pages.TeamPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["navigation"])
class TeamTest : LoginTestBase() {

    @Test
    fun `can see team members`() {
        login(User.QUEEN)
            .goTo(TeamPage(driver))
            .selectTab("Members")
            .apply {
                User.values().forEach {
                    selectMember(it.uiName)
                }
            }
    }
}