package e2e.test.space

import e2e.model.Url
import e2e.model.User
import e2e.pages.BasePage.Companion.view
import e2e.pages.BasePage.Companion.waitForSeconds
import e2e.pages.space.HomePage
import e2e.pages.space.LoginPage
import e2e.pages.space.TodoPage
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class TodoTest : TestBase(url = Url.SPACE.url) {

    private val task1 = TodoPage.State()
    private val task2 = TodoPage.State()

    @Test
    fun `01 login test`() {
        LoginPage(driver)
            .isOpened("e2e")
            .login(User.KING)
            .view(HomePage(driver))
    }

    @Test
    fun `02 todo list test`() {
        HomePage(driver)
            .goTo(TodoPage(driver))
            .selectTab("History")
            .selectTab("Today")
            .addTask(task1.name)
            .addTask(task2.name)
            .waitForSeconds(3)
            .selectTab("Later")
            .waitForSeconds(3)
    }
}