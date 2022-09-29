package e2e.space.test.task

import e2e.space.model.User
import e2e.space.pages.TodoPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["task"])
class TaskTest : LoginTestBase() {

    private val task1 = TodoPage.State()

    @Test(description = "tab navigation: open task page on today's tab")
    fun `tab navigation`() {
        login(User.KING)
            .goTo(TodoPage(driver))
            .selectTab("History")
            .selectTab("Later")
            .selectTab("Today")
    }

    @Test(dependsOnMethods = ["tab navigation"])
    fun `add task`() {
        TodoPage(driver)
            .addTask(task1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add task"])
    fun `select task`() {
        TodoPage(driver)
            .selectTask(task1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add task", "select task"])
    fun `un-select task`() {
        TodoPage(driver)
            .unselectTask(task1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add task", "select task", "un-select task"])
    fun `delete task`() {
        TodoPage(driver)
            .deleteTask(task1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add task", "select task", "un-select task", "delete task"])
    fun `undo deleted task`() {
        TodoPage(driver)
            .undoDeletedTask()
    }

    @Test(dependsOnMethods = ["tab navigation", "add task", "select task", "un-select task", "delete task", "undo deleted task"])
    fun `deleted task finally`() {
        TodoPage(driver)
            .deleteTask(task1.name)
    }
}