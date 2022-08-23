package e2e.model

enum class User(val username: String, val password: String, val uiName: String) {
    KING("king.selenium", "selenium", "King Selenium"),
    QUEEN("queen.selenium", "selenium", "Queen Selenium"),
    ROOK("rook.selenium", "selenium", "Rook Selenium"),
}