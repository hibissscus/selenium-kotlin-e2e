package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class MergeRequestPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.MERGE_REQUEST.title
    }

    override fun opened(s: String): MergeRequestPage = apply {
        super.opened(title())
    }

}