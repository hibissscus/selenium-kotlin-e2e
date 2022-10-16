package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class BlogPostPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.BLOG_POST.title
    }

    override fun opened(s: String): BlogPostPage = apply {
        super.opened(title())
    }

}