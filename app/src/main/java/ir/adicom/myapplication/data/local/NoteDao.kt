package ir.adicom.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    fun getItem(id: Int): NoteEntity

    @Insert
    fun insertItem(noteEntity: NoteEntity): Long

    @Update
    fun updateItem(noteEntity: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteItem(id: Int)
}