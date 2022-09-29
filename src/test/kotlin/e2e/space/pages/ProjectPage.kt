package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ProjectPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val newProject_ = ".XButtonStyles-button"
    }

    @FindBy(css = newProject_)
    private lateinit var newProject: WebElement

    override fun href(): String {
        return "/p"
    }

    override fun name(): String {
        return PageTitles.PROJECTS.title
    }

    override fun opened(s: String): ProjectPage = apply {
        urlContains(href())
        textToBe(pageHeader, name())
        textToBe(newProject, "New project")
    }
}