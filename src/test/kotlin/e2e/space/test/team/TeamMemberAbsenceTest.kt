package e2e.space.test.team

import e2e.space.model.AbsenceReason
import e2e.space.model.User
import e2e.space.pages.TeamPage
import e2e.space.pages.create.AbsencePage
import e2e.space.test.login.LoginTestBase
import org.testng.annotations.Test

@Test(groups = ["team"])
class TeamMemberAbsenceTest : LoginTestBase() {

    private val absence = AbsencePage.Absence(reason = AbsenceReason.SICK_LEAVE)

    @Test
    fun `can navigate to absence page`() {
        login(User.QUEEN)
            .goTo(AbsencePage(driver))
    }

    @Test(dependsOnMethods = ["can navigate to absence page"])
    fun `create new absence`() {
        AbsencePage(driver)
            .createNewAbsence(absence)
            .submit()
            .checkError()
            .selectReason(absence.reason)
            .submit()
            .goTo(TeamPage(driver))
    }

    @Test(dependsOnMethods = ["can navigate to absence page", "create new absence"])
    fun `check member availability`() {
        TeamPage(driver)
            .selectTab("Members")
            .selectMember(User.QUEEN.uiName)
            .viewProfilePage(User.QUEEN)
            .checkAvailability(absence.description)
    }
}