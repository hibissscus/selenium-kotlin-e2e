package e2e.space.test.create

import e2e.space.model.User
import e2e.space.pages.TeamPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["team"])
class CreateTest : LoginTestBase() {

    @Test
    fun `can navigate to team page`() {
        login(User.KING)
            .goTo(TeamPage(driver))
    }

}