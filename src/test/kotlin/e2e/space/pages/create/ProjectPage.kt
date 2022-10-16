package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class ProjectPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.PROJECT.title
    }

    override fun opened(s: String): ProjectPage = apply {
        super.opened(title())
    }

}