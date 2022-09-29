package e2e.space.test.login

import e2e.space.model.User
import org.testng.annotations.Test

@Test(groups = ["user"])
open class UserTest : LoginTestBase() {

    @Test
    fun `login with different users`() {
        User.values().forEach {
            login(it).logout()
        }
    }
}