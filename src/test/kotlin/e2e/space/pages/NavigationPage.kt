package e2e.space.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

abstract class NavigationPage(driver: WebDriver) : BasePage(driver) {

    companion object Path {
        const val app_ = "#app"
        const val dialog_ = "#dialog"
        const val loader_ = "#loader div:last-child div:last-child "

        const val sidebarHeader_ = ".AppStyles-Sidebar-sidebarHeader"
        const val pageHeader_ = ".XStyles-pageHeader"
        const val scrollableTabs_ = ".XTabsStyles-scrollableTabs"
    }

    @FindBy(css = loader_)
    private lateinit var loader: WebElement

    @FindBy(css = sidebarHeader_)
    protected lateinit var sidebarHeader: WebElement

    @FindBy(css = pageHeader_)
    protected lateinit var pageHeader: WebElement

    @FindBy(css = scrollableTabs_)
    protected lateinit var scrollableTabs: WebElement

    override fun opened(s: String): NavigationPage = apply {
        presence(By.cssSelector(app_))
        presence(By.cssSelector(dialog_))
        presence(By.cssSelector(loader_))
    }

    /**
     * Check that on [NavigationPage] are no loading process.
     */
    override fun loaded(): NavigationPage = apply {
        super.loaded()
        attributeContains(loader, "class", "XLoaderStyles-invisibleLoader")
    }

    @FindBy(css = ".AppStyles-Navigation-item [aria-label='Chats']")
    private lateinit var chats: WebElement

    @FindBy(css = "[aria-label='Projects'].AppStyles-Navigation-item")
    private lateinit var projects: WebElement

    @FindBy(css = "[aria-label='Blog'].AppStyles-Navigation-item")
    private lateinit var blog: WebElement

    @FindBy(css = "[aria-label='Teams'].AppStyles-Navigation-item")
    private lateinit var teams: WebElement

    @FindBy(css = "[aria-label='Administration'].AppStyles-Navigation-item")
    private lateinit var administration: WebElement

    @FindBy(css = "[aria-label='To-Do List'].XApplicationSidebarStyles-dropdownItem")
    private lateinit var todoList: WebElement

    @FindBy(css = "[title='e2e']")
    private lateinit var logout: WebElement

    @FindBy(css = ".icon-sign-out")
    private lateinit var signOut: WebElement

    open fun <T : BasePage> goToPage(element: WebElement, page: T): T = page.apply {
        view(element, this)
    }

    open fun <T : BasePage> goTo(page: T): T = page.apply {
        when (page) {
            is AdminPage -> goToPage(administration, page)
            is BlogPage -> goToPage(blog, page)
            is LoginPage -> click(logout).also { goToPage(signOut, page) }
            is ProjectPage -> goToPage(projects, page)
            is TeamPage -> goToPage(teams, page)
            is TodoPage -> goToPage(todoList, page)
        }
    }

    fun clickOnTab(tabName: String): NavigationPage = apply {
        visible(scrollableTabs)
        val tabElement = visibleAsNested(
            By.cssSelector(scrollableTabs_),
            By.xpath(".//*[contains(text(),'${tabName}')]")
        ).first()
        click(tabElement)
        attributeContains(tabElement, "class", "XTabsStyles-selectedTab")
    }

    fun logout(): LoginPage {
        return goTo(LoginPage(driver))
    }

}