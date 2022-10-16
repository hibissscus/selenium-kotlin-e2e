package e2e.space.test.navigation

import e2e.space.model.User
import e2e.space.pages.HomePage
import e2e.space.pages.create.AbsencePage
import e2e.space.pages.create.BlogPostPage
import e2e.space.pages.create.ChannelPage
import e2e.space.pages.create.CodeReviewPage
import e2e.space.pages.create.DocumentPage
import e2e.space.pages.create.MergeRequestPage
import e2e.space.pages.create.ProjectPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["navigation"])
class CreatePagesNavigationTest : LoginTestBase() {

    @Test
    fun `can switch between create pages`() {
        login(User.QUEEN).apply {
            listOf(
                AbsencePage(driver),
                BlogPostPage(driver),
                ChannelPage(driver),
                CodeReviewPage(driver),
                DocumentPage(driver),
                MergeRequestPage(driver),
                ProjectPage(driver)
            ).forEach {
                HomePage(driver)
                    .goTo(it)
                    .close()
            }
        }
    }
}