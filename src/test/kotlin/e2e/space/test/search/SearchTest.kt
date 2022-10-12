package e2e.space.test.search

import e2e.space.model.User
import e2e.space.pages.SearchPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["team"])
class SearchTest : LoginTestBase() {

    @Test
    fun `can navigate to search page`() {
        login(User.KING)
            .goTo(SearchPage(driver))
            .close()
    }

}