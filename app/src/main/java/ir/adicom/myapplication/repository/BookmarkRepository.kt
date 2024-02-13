package ir.adicom.myapplication.repository

import ir.adicom.myapplication.database.AppDatabase
import ir.adicom.myapplication.database.asDomainModel
import ir.adicom.myapplication.domain.User
import ir.adicom.myapplication.domain.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    val users: Flow<List<User>?> =
        appDatabase.usersDao.getBookmarkUsers().map { it?.asDomainModel() }

    suspend fun addUser(user: User) {
        try {
            appDatabase.usersDao.insertBookmarkUser(user.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}