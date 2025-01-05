package ir.adicom.myapplication.core.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.adicom.myapplication.ComposeApplication

@Database(entities = [NoteEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        private var _instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (_instance == null) {
                _instance = Room.databaseBuilder(
                    ComposeApplication.appContext,
                    AppDatabase::class.java,
                    "notes_db"
                ).build()
            }

            return _instance!!
        }
    }
}