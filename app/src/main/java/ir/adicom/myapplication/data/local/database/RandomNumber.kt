package ir.adicom.myapplication.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class RandomNumber(
    val value: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface RandomNumberDao {
    @Query("SELECT * FROM RandomNumber ORDER BY uid DESC LIMIT 10")
    fun getRandomNumbers(): Flow<List<RandomNumber>>
    @Query("SELECT COUNT(*) FROM RandomNumber")
    fun getRandomNumbersCount(): Flow<Int>

    @Insert
    suspend fun insertRandomNumber(item: RandomNumber)
}
