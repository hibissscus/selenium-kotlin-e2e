package e2e.space.pages.create

import e2e.space.model.AbsenceReason
import e2e.space.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.random.Random

class AbsencePage(driver: WebDriver) : DialogPage(driver) {

    data class Absence(
        var random: Int = Random.nextInt(10000, 50000),
        var reason: AbsenceReason = AbsenceReason.SICK_LEAVE,
        var description: String = "Absence $random",
    )

    companion object Path {
        const val reason_ = "input[placeholder='Select reason'].XTextFieldStyles-textField"
        const val description_ = "input[placeholder='Description'].XTextFieldStyles-textField"
        const val valueError_ = "[title='Please enter a value'].XFormFieldStyles-error"
    }

    @FindBy(css = reason_)
    private lateinit var reason: WebElement

    @FindBy(css = description_)
    private lateinit var description: WebElement

    @FindBy(css = valueError_)
    private lateinit var valueError: WebElement

    override fun title(): String {
        return PageTitles.ABSENCE.title
    }

    override fun opened(s: String): AbsencePage = apply {
        super.opened(title())
    }

    override fun submit(): AbsencePage = apply {
        super.submit()
    }

    fun checkError(): AbsencePage = apply {
        visible(valueError)
    }

    fun createNewAbsence(absence: Absence): AbsencePage = apply {
        sendText(description, absence.description)
    }

    fun selectReason(absenceReason: AbsenceReason): AbsencePage = apply {
        sendText(By.cssSelector(reason_ + ", input[value='${absenceReason.value}'].XTextFieldStyles-textField"), absenceReason.value)
        click(By.xpath("//*[contains(text(),'${absenceReason.value}') and ancestor-or-self::*${listitem_}]"))
        visible(By.cssSelector("input[value='${absenceReason.value}'].XTextFieldStyles-textField"))
    }

}