package e2e.space.pages

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import kotlin.random.Random

class TodoPage(driver: WebDriver) : NavigationPage(driver) {

    data class State(
        var random: Int = Random.nextInt(10000, 50000),
        var name: String = "Task $random",
    )

    companion object Path {
        const val newTask_ = "[placeholder='Add a task'].XTextFieldStyles-textField"
        const val itemCheckbox_ = ".TodoItemComponentStyles-itemCheckbox"
        const val actionButtonDelete_ = ".MessageActionsPopupStyles-actionButtonDelete"
        const val notification_ = "SingleActionNotificationStyles-notification"
    }

    @FindBy(css = newTask_)
    private lateinit var newTask: WebElement

    override fun opened(s: String): TodoPage = apply {
        visible(newTask)
    }

    fun selectTab(tabName: String): TodoPage = apply {
        clickOnTab(tabName)
    }

    fun addTask(taskName: String): TodoPage = apply {
        sendText(newTask, taskName)
        newTask.sendKeys(Keys.ENTER)
    }

    fun selectTask(taskName: String): TodoPage = apply {
        presenceOfNestedElementLocatedBy(
            By.xpath("//*[@role='listitem'][.//*[contains(text(),'${taskName}')]]"),
            By.cssSelector(itemCheckbox_)
        ).click()
    }

    fun deleteTask(taskName: String): TodoPage = apply {
        Actions(driver).moveToElement(
            presence(
                By.xpath("//*[@role='listitem'][.//*[contains(text(),'${taskName}')]]"),
            )
        ).click().perform()
        Actions(driver).moveToElement(
            presenceOfNestedElementLocatedBy(
                By.xpath("//*[@role='listitem'][.//*[contains(text(),'${taskName}')]]"),
                By.cssSelector(actionButtonDelete_)
            )
        ).click().perform()
    }

    fun undoDeletedTask(): TodoPage = apply {
        visibilityOfAllElementsLocatedBy(
            By.xpath("//*[contains(@class,'$notification_')]//*[contains(text(),'Undo')]")
        ).first().click()
    }
}