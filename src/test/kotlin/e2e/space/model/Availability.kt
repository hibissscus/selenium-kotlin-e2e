package e2e.space.model

enum class Availability(val value: String) {
    MINUTES_15("15 minutes"),
    MINUTES_30("30 minutes"),
    HOURS_1("1 hour"),
    HOURS_2("2 hours"),
    HOURS_4("24 hours"),
    TODAY("Today"),
    WEEK("This week"),
}