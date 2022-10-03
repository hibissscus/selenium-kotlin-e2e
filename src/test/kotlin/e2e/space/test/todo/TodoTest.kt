package e2e.space.test.todo

import e2e.space.model.User
import e2e.space.pages.TodoPage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["todo"])
class TodoTest : LoginTestBase() {

    private val todo1 = TodoPage.State()

    @Test(description = "tab navigation: open todo page on today's tab")
    fun `tab navigation`() {
        login(User.KING)
            .goTo(TodoPage(driver))
            .selectTab("History")
            .selectTab("Later")
            .selectTab("Today")
    }

    @Test(dependsOnMethods = ["tab navigation"])
    fun `add todo`() {
        TodoPage(driver)
            .addTask(todo1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add todo"])
    fun `select todo`() {
        TodoPage(driver)
            .selectTask(todo1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add todo", "select todo"])
    fun `un-select todo`() {
        TodoPage(driver)
            .unselectTask(todo1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add todo", "select todo", "un-select todo"])
    fun `delete todo`() {
        TodoPage(driver)
            .deleteTask(todo1.name)
    }

    @Test(dependsOnMethods = ["tab navigation", "add todo", "select todo", "un-select todo", "delete todo"])
    fun `undo deleted todo`() {
        TodoPage(driver)
            .undoDeletedTask()
    }

    @Test(dependsOnMethods = ["tab navigation", "add todo", "select todo", "un-select todo", "delete todo", "undo deleted todo"])
    fun `deleted todo finally`() {
        TodoPage(driver)
            .deleteTask(todo1.name)
    }
}