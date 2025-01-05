package ir.adicom.myapplication.models

import ir.adicom.myapplication.data.local.NoteEntity

data class NoteModel(
    val id: Int,
    val title: String,
    val description: String,
)

fun NoteModel.toEntity(): NoteEntity {
    return NoteEntity(
        id, title, description
    )
}

fun dummyNotes(): ArrayList<NoteModel> {
    val notes = arrayListOf<NoteModel>()
    for (i in 1..2) {
        notes.add(
            NoteModel(i, "Title $i", "Description $i")
        )
    }
    return notes
}

