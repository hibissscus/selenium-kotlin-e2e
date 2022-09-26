package e2e.space.test.login

import e2e.space.model.User
import org.testng.annotations.Test

@Test(groups = ["user"])
open class UserTest : LoginBase() {

    @Test
    fun `login with king`() {
        login(User.KING)
            .logout()
    }

    @Test
    fun `login with queen`() {
        login(User.QUEEN)
            .logout()
    }

    @Test
    fun `login with rook`() {
        login(User.ROOK)
            .logout()
    }
}