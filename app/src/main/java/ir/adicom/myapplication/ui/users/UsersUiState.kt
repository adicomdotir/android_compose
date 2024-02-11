package ir.adicom.myapplication.ui.users

import ir.adicom.myapplication.domain.User

data class UsersUiState(
    val list: List<User> = listOf(),
    val offline: Boolean = false
)