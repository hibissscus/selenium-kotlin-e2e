package e2e.pages.space

import e2e.pages.NavigationPage
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.ExpectedConditions.*
import kotlin.random.Random

class TodoPage(driver: WebDriver) : NavigationPage(driver) {

    data class State(
        var random: Int = Random.nextInt(10000, 50000),
        var name: String = "Task $random",
    )

    companion object Path {
        const val newTask_ = "[placeholder='Add a task'].XTextFieldStyles-textField"
    }

    @FindBy(css = newTask_)
    private lateinit var newTask: WebElement

    override fun isOpened(s: String): TodoPage = apply {
        visible(newTask)
    }

    fun selectTab(tabName: String): TodoPage = apply {
        clickOnTab(tabName)
    }

    fun addTask(taskName: String): TodoPage = apply {
        sendText(newTask, taskName)
        newTask.sendKeys(Keys.ENTER)
    }

    fun deleteTask(taskName: String): TodoPage = apply {
        wait().until(
            presenceOfNestedElementLocatedBy(
                By.xpath("//*[contains(text(),'${taskName}')]"),
                By.cssSelector(".MessageActionsPopupStyles-actionButtonDelete")
            )
        ).click()
    }
}