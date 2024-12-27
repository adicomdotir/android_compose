package ir.adicom.myapplication.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Category(
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM Category WHERE uid=:uid")
    fun getCategoryById(uid: Int): Flow<Category>

    @Insert
    suspend fun insertCategory(item: Category)

    @Delete
    suspend fun deleteCategory(item: Category)

    @Update
    suspend fun updateCategory(item: Category)
}
