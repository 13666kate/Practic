package sence.kate.practica3.DataClasses

data class User(
    val name: String,
    val login: String,
    val password: String,
    val userAppType: UserAppType = UserAppType.FREE
)

enum class UserAppType {
    FREE,
    PAID
}