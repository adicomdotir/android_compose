package ir.adicom.myapplication.models

data class NoteModel(
    val id: Int,
    val title: String,
    val description: String,
)

fun dummyNotes(): ArrayList<NoteModel> {
    val notes = arrayListOf<NoteModel>()
    for (i in 1..20) {
        notes.add(
            NoteModel(i, "Title $i", "Description $i")
        )
    }
    return notes
}
