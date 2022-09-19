package e2e.space.pages.space

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement

class ProjectPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val newProject_ = ".XButtonStyles-button"
    }

    @FindBy(css = newProject_)
    private lateinit var newProject: WebElement

    override fun isOpened(s: String): ProjectPage = apply {
        wait().until(textToBePresentInElement(pageHeader, PageTitles.PROJECTS.title))
        wait().until(textToBePresentInElement(newProject, "New project"))
    }
}