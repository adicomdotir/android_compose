package ir.adicom.myapplication.feature_addNote.presentation

sealed interface AddNoteAction {
    data class TitleOnValueChange(val value: String) : AddNoteAction
    data class DescriptionOnValueChange(val value: String) : AddNoteAction
    object BackIconOnClick : AddNoteAction
    object ShowConfirmationDialog : AddNoteAction
    object HideConfirmationDialog : AddNoteAction
    object DeleteNote : AddNoteAction
}

sealed class AddNoteEvent {
    object NavigateBack : AddNoteEvent()
}