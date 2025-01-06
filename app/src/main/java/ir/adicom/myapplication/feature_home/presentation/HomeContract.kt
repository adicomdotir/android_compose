package ir.adicom.myapplication.feature_home.presentation

sealed interface HomeAction {
    data class ListItemOnClick(val value: Int) : HomeAction
    object AddNewNote : HomeAction
}

sealed class HomeEvent {
    data class NavigateNext(val route: String) : HomeEvent()
}