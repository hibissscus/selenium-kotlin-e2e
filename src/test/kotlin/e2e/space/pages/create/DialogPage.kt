package e2e.space.pages.create

import e2e.space.pages.NavigationPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

open class DialogPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val dialog_ = "#dialog"
        const val dialogElement_ = ".XDialogStyles-dialogElement"
        const val close_ = ".XDialogStyles-closeIcon"
    }

    @FindBy(css = dialog_)
    private lateinit var dialog: WebElement

    @FindBy(css = dialogElement_)
    private lateinit var dialogElement: WebElement

    @FindBy(css = close_)
    private lateinit var close: WebElement

    override fun title(): String {
        return ""
    }

    override fun opened(s: String): DialogPage = apply {
        textToBe(dialogElement, s)
    }

    fun close(): DialogPage = apply {
        click(close)
        textToBe(dialog, "")
    }

}