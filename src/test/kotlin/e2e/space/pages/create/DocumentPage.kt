package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class DocumentPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.DOCUMENT.title
    }

    override fun opened(s: String): DocumentPage = apply {
        super.opened(title())
    }

}