package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.random.Random

class TodoPage(driver: WebDriver) : NavigationPage(driver) {

    data class State(
        var random: Int = Random.nextInt(10000, 50000),
        var name: String = "Task $random",
    )

    companion object Path {
        const val newTask_ = "[placeholder='Add a task'].XTextFieldStyles-textField"
        const val itemCheckbox = "TodoItemComponentStyles-itemCheckbox"
        const val itemCheckboxChecked = "XCheckboxStyles-checkboxChecked"
        const val itemCheckboxUnchecked = "XCheckboxStyles-checkboxPlain"
        const val actionButtonDelete_ = "[aria-label='Delete']"
        const val notification_ = "SingleActionNotificationStyles-notification"
    }

    @FindBy(css = newTask_)
    private lateinit var newTask: WebElement

    override fun title(): String {
        return PageTitles.TODO.title
    }

    override fun opened(s: String): TodoPage = apply {
        visible(newTask)
    }

    fun selectTab(tabName: String): TodoPage = apply {
        selectTabByName(tabName)
    }

    fun addTask(taskName: String): TodoPage = apply {
        sendText(newTask, taskName)
        newTask.sendKeys(Keys.ENTER)
    }

    private fun selectUnselectTask(taskName: String, checked: Boolean = true) {
        val checkboxXpath = "//*[@role='listitem'][.//*[contains(text(),'${taskName}')]]//*[contains(@class,'$itemCheckbox')"
        click(By.xpath(checkboxXpath + "and contains(@class,${if (checked) itemCheckboxUnchecked else itemCheckboxChecked})]"), true)
        presence(By.xpath(checkboxXpath + "and contains(@class,${if (checked) itemCheckboxChecked else itemCheckboxUnchecked})]"))
    }

    fun selectTask(taskName: String): TodoPage = apply {
        selectUnselectTask(taskName, true)
    }

    fun unselectTask(taskName: String): TodoPage = apply {
        selectUnselectTask(taskName, false)
    }

    fun deleteAllTasks(): TodoPage = apply {
        if (isPresent(By.xpath("//*[@role='listitem']"))) {
            val todos = presenceOfAllElementsLocatedBy(By.xpath("//*[@role='listitem']")).map { it.text }.toList()
            todos.forEach { deleteTask(it) }
        }
    }

    fun deleteTask(taskName: String): TodoPage = apply {
        click(By.xpath("//*[contains(text(),'${taskName}') and ancestor-or-self::*$listitem_]"))
        click(
            presenceOfNestedElementLocatedBy(
                By.xpath("//*[@role='listitem'][.//*[contains(text(),'${taskName}')]]"), By.cssSelector(actionButtonDelete_)
            )
        )
        visibilityOfAllElementsLocatedBy(
            By.xpath("//*[contains(@class,'$notification_')]//*[contains(text(),'Undo')]")
        )
        waitForLoaded()
    }

    fun undoDeletedTask(): TodoPage = apply {
        visibilityOfAllElementsLocatedBy(
            By.xpath("//*[contains(@class,'$notification_')]//*[contains(text(),'Undo')]")
        ).first().click()
    }
}