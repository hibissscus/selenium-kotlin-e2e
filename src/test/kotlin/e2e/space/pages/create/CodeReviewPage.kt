package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class CodeReviewPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.CODE_REVIEW.title
    }

    override fun opened(s: String): CodeReviewPage = apply {
        super.opened(title())
    }

}