package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class AbsencePage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.ABSENCE.title
    }

    override fun opened(s: String): AbsencePage = apply {
        super.opened(title())
    }

}