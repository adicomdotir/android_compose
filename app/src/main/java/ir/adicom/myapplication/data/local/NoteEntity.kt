package ir.adicom.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.adicom.myapplication.models.NoteModel

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?
)

fun NoteEntity.toModel(): NoteModel {
    return NoteModel(
        this.id ?: -1,
        this.title ?: "",
        this.description ?: ""
    )
}
