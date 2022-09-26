package e2e.space.test.task

import e2e.space.model.User
import e2e.space.pages.TodoPage
import e2e.space.test.login.LoginBase
import org.testng.annotations.Test

@Test(groups = ["task"])
class TaskTest : LoginBase() {

    private val task1 = TodoPage.State()

    @Test
    fun `list navigation`() {
        login(User.KING)
            .goTo(TodoPage(driver))
            .selectTab("History")
            .selectTab("Later")
            .selectTab("Today")
    }

    @Test(dependsOnMethods = ["list navigation"])
    fun `add task`() {
        TodoPage(driver)
            .addTask(task1.name)
    }

    @Test(dependsOnMethods = ["list navigation", "add task"])
    fun `select task`() {
        TodoPage(driver)
            .selectTask(task1.name)
    }

    @Test(dependsOnMethods = ["list navigation", "add task", "select task"])
    fun `un-select task`() {
        TodoPage(driver)
            .selectTask(task1.name)
    }

    @Test(dependsOnMethods = ["list navigation", "add task", "select task", "un-select task"])
    fun `delete task`() {
        TodoPage(driver)
            .deleteTask(task1.name)
    }

    @Test(dependsOnMethods = ["list navigation", "add task", "select task", "un-select task", "delete task"])
    fun `undo deleted task`() {
        TodoPage(driver)
            .undoDeletedTask()
    }

    @Test(dependsOnMethods = ["list navigation", "add task", "select task", "un-select task", "delete task", "undo deleted task"])
    fun `deleted task finally`() {
        TodoPage(driver)
            .deleteTask(task1.name)
    }
}