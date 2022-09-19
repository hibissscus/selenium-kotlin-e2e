package e2e.space.test.task

import e2e.space.model.Url
import e2e.space.model.User
import e2e.space.pages.BasePage.Companion.view
import e2e.space.pages.HomePage
import e2e.space.pages.LoginPage
import e2e.space.pages.TodoPage
import org.testng.SkipException
import org.testng.annotations.Test
import testee.it.e2e.core.test.TestBase

class TodoTest : TestBase(url = Url.SPACE.url) {

    private val task1 = TodoPage.State()
    private val task2 = TodoPage.State()

    @Test
    fun `01 login test`() {
        LoginPage(driver)
            .opened("e2e")
            .login(User.KING)
            .view(HomePage(driver))
    }

    @Test
    fun `02 todo list navigation`() {
        HomePage(driver)
            .goTo(TodoPage(driver))
            .selectTab("History")
            .selectTab("Later")
            .selectTab("Today")
    }

    @Test
    fun `03 todo addTask`() {
        TodoPage(driver).addTask(task1.name)
    }

    @Test
    fun `04 todo select task`() {
        TodoPage(driver).selectTask(task1.name)
    }

    @Test
    fun `05 todo un-select task`() {
        TodoPage(driver).selectTask(task1.name)
    }

    @Test
    fun `06 todo delete task`() {
        TodoPage(driver).deleteTask(task1.name)
    }

    @Test
    fun `07 todo undo deleted task`() {
        TodoPage(driver).undoDeletedTask()
    }

    @Test
    fun `08 todo deleted task finally`() {
        TodoPage(driver).deleteTask(task1.name)
    }

    @Test
    fun `09 some exception`() {
        // just for demonstration purposes
        assert(false) { "this test failed" }
    }

    @Test
    fun `10 skipped test`() {
        // just for demonstration purposes
        throw SkipException("this will be skipped")
    }
}