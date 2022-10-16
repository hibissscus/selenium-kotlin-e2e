package e2e.space.pages

import e2e.space.model.Availability
import e2e.space.pages.create.AbsencePage
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
        const val tabs_ = ".XTabsStyles-tabs"
        const val availability_ = "[data-spotlight-hint-id-cbbcehiace-availability-status*= 'availability-status']"
        const val moon_ = ".icon-moon-small"
        const val bell_ = ".icon-bell-small"
        const val bellCrossed_ = ".icon-bell-crossed-small"

        const val navigationTopPart_ = ".NavigationBarStyles-topPart"
        const val navigationBottomPart_ = ".NavigationBarStyles-bottomPart"
        const val navigationItem_ = ".AppStyles-Navigation-item"
        const val navigationDropdownItem_ = ".XApplicationSidebarStyles-dropdownItem"
        const val quickAccessListItem_ = ".QuickAccessPanelStyles-listItem"
        const val toggleNormal_ = ".XSimpleToggleStyles-toggleNormal"
        const val toggleActive = "XSimpleToggleStyles-toggleActiveNormal"
        const val toggleInactive = "XSimpleToggleStyles-toggleInactiveNormal"
    }

    @FindBy(css = loader_)
    private lateinit var loader: WebElement

    @FindBy(css = sidebarHeader_)
    protected lateinit var sidebarHeader: WebElement

    @FindBy(css = pageHeader_)
    protected lateinit var pageHeader: WebElement

    @FindBy(css = tabs_)
    protected lateinit var tabs: WebElement

    @FindBy(css = availability_)
    protected lateinit var availability: WebElement

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
        isVisible(loader)
        isInvisible(loader)
        attributeContains(loader, "class", "XLoaderStyles-invisibleLoader")
    }

    @FindBy(css = navigationTopPart_)
    private lateinit var navigationTopPart: WebElement

    @FindBy(css = navigationBottomPart_)
    private lateinit var navigationBottomPart: WebElement

    @FindBy(css = ".NavigationBarStyles-moreToggle")
    private lateinit var more: WebElement

    @FindBy(css = ".XApplicationSidebarStyles-drawer")
    private lateinit var rootWithFullSidebar: WebElement

    @FindBy(css = "[aria-label='Search']$navigationItem_")
    private lateinit var search: WebElement

    @FindBy(css = "[aria-label='Chats']$navigationItem_")
    private lateinit var chats: WebElement

    @FindBy(css = "[aria-label='Projects']$navigationItem_")
    private lateinit var projects: WebElement

    @FindBy(css = "[aria-label='Blog']$navigationItem_")
    private lateinit var blog: WebElement

    @FindBy(css = "[aria-label='Teams']$navigationItem_")
    private lateinit var teams: WebElement

    @FindBy(css = "[aria-label='Administration']$navigationItem_")
    private lateinit var administration: WebElement

    @FindBy(css = "[aria-label='To-Do List']$navigationDropdownItem_")
    private lateinit var todoList: WebElement

    @FindBy(css = ".icon-create")
    private lateinit var create: WebElement

    @FindBy(css = "[title='e2e']")
    private lateinit var logout: WebElement

    @FindBy(css = ".icon-sign-out")
    private lateinit var signOut: WebElement

    open fun <T : BasePage> goToPage(element: WebElement, page: T, title: String = ""): T = page.apply {
        if (isClickable(element)) view(element, this, title) else {
            switchOnQuickAccessPage(page)
            view(element, this, title)
        }
    }

    fun switchAllQuickAccessPages(on: Boolean = true, pageName: String? = null): NavigationPage = apply {
        if (!isClickable(rootWithFullSidebar)) {
            click(more)
            visible(rootWithFullSidebar)
        }
        presenceOfAllElementsLocatedBy(By.cssSelector(quickAccessListItem_)).filter { pageName == null || pageName == it.text }.forEach {
            val toggle = visibilityOfNestedElementsLocatedBy(it, (By.cssSelector(toggleNormal_))).first()
            if (toggle.getAttribute("class").contains(if (on) toggleInactive else toggleActive)) {
                click(toggle)
                attributeContains(toggle, "class", if (on) toggleActive else toggleInactive)
                if (on) presence(By.cssSelector("[aria-label='${it.text}']$navigationItem_,$navigationDropdownItem_"))
            }
        }
    }

    fun <T : BasePage> switchOnQuickAccessPage(page: T): NavigationPage = apply {
        switchAllQuickAccessPages(true, page.title())
    }

    open fun <T : BasePage> goTo(page: T): T = page.apply {
        when (page) {
            is AdminPage -> goToPage(administration, page)
            is BlogPage -> goToPage(blog, page)
            is LoginPage -> click(logout).also { goToPage(signOut, page, "e2e") }
            is ProjectsPage -> goToPage(projects, page)
            is TeamPage -> goToPage(teams, page)
            is TodoPage -> goToPage(todoList, page)
            is SearchPage -> goToPage(search, page)
        }
    }

    protected fun selectTabByName(tabName: String): NavigationPage = apply {
        visible(tabs)
        val tabElement = visibilityOfNestedElementsLocatedBy(
            By.cssSelector(tabs_), By.xpath(".//*[contains(text(),'${tabName}')]")
        ).first()
        click(tabElement)
        attributeContains(tabElement, "class", "XTabsStyles-tab")
        attributeContains(tabElement, "class", "XTabsStyles-selectedTab")
    }

    protected fun changeAvailability(on: Boolean, availabilityTime: Availability): NavigationPage = apply {
        click(availability)
    }

    open fun <T : BasePage> create(page: T): T = page.apply {
        when (page) {
            is AbsencePage -> goToPage(administration, page)
        }
    }

    fun logout(): LoginPage {
        return goTo(LoginPage(driver))
    }

}