package e2e.space.pages

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ProjectsPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val newProject_ = ".XButtonStyles-button"
    }

    @FindBy(css = newProject_)
    private lateinit var newProject: WebElement

    override fun href(): String {
        return "/p"
    }

    override fun title(): String {
        return PageTitles.PROJECTS.title
    }

    override fun opened(s: String): ProjectsPage = apply {
        urlContains(href())
        textToBe(pageHeader, title())
        textToBe(newProject, "New project")
    }
}