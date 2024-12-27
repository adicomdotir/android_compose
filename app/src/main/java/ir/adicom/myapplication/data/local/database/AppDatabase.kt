package ir.adicom.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class,  Category::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myModelDao(): MyModelDao
    abstract fun categoryDao(): CategoryDao
}
