package ir.adicom.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class, RandomNumber::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myModelDao(): MyModelDao
    abstract fun randomNumberDao(): RandomNumberDao
}
