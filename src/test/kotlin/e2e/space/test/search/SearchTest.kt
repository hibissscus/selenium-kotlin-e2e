package e2e.space.test.search

import e2e.space.model.User
import e2e.space.pages.SearchPage
import e2e.space.pages.create.*
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["search"])
class SearchTest : LoginTestBase() {

    @Test
    fun `can navigate to search page`() {
        login(User.ROOK)
            .goTo(SearchPage(driver))
    }

    @Test(dependsOnMethods = ["can navigate to search page"])
    fun `navigate to create pages via search`() {
        listOf(
            AbsencePage(driver),
            BlogPostPage(driver),
            ChannelPage(driver),
            CodeReviewPage(driver),
            DocumentPage(driver),
            MergeRequestPage(driver)
        ).forEach {
            SearchPage(driver)
                .goTo(it)
                .close()
                .goTo(SearchPage(driver))
        }
    }
}