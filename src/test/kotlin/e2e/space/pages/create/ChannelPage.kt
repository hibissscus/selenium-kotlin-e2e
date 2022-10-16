package e2e.space.pages.create

import e2e.space.model.PageTitles
import org.openqa.selenium.WebDriver

class ChannelPage(driver: WebDriver) : DialogPage(driver) {

    companion object Path {}

    override fun title(): String {
        return PageTitles.CHANNEL.title
    }

    override fun opened(s: String): ChannelPage = apply {
        super.opened(title())
    }

}