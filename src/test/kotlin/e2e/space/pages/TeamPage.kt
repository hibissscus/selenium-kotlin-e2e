package e2e.space.pages

import e2e.space.model.Availability
import e2e.space.model.PageTitles
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class TeamPage(driver: WebDriver) : NavigationPage(driver) {

    companion object Path {
        const val teamDirectory_ = ".TeamDirectoryStyles-tabContent"
        const val memberFilter_ = "input[placeholder='Type to filter'].XTextFieldStyles-textField"
        const val memberProfileCard_ = ".MemberProfileCardStyles-container"
    }

    @FindBy(css = teamDirectory_)
    private lateinit var teamDirectory: WebElement

    @FindBy(css = memberFilter_)
    private lateinit var memberFilter: WebElement

    @FindBy(css = memberProfileCard_)
    private lateinit var memberProfileCard: WebElement

    override fun href(): String {
        return "/team"
    }

    override fun name(): String {
        return PageTitles.TEAMS.title
    }

    override fun opened(s: String): TeamPage = apply {
        urlContains(href())
        textToBe(sidebarHeader, name())
    }

    fun selectTab(tabName: String): TeamPage = apply {
        selectTabByName(tabName)
    }

    fun selectMember(memberName: String): TeamPage = apply {
        visibilityOfNestedElementsLocatedBy(By.cssSelector(teamDirectory_), By.cssSelector(memberFilter_))
        sendText(memberFilter, memberName)
        textToBe(By.cssSelector(memberProfileCard_), memberName)
    }

    fun changeAvailability(): TeamPage = apply {
        super.changeAvailability(true, Availability.HOURS_1)
    }

}