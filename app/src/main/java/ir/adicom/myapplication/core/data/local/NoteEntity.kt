package ir.adicom.myapplication.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.adicom.myapplication.core.domain.models.NoteModel

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
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
