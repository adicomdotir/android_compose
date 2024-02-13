package ir.adicom.myapplication.ui.bookmark

import ir.adicom.myapplication.domain.User

data class BookmarkUiState(
    val list: List<User> = listOf()
)