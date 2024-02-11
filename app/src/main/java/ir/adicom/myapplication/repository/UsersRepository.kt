package ir.adicom.myapplication.repository

import ir.adicom.myapplication.database.AppDatabase
import ir.adicom.myapplication.database.asDomainModel
import ir.adicom.myapplication.domain.User
import ir.adicom.myapplication.network.UsersApi
import ir.adicom.myapplication.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val appDatabase: AppDatabase
) {

    val users: Flow<List<User>?> =
        appDatabase.usersDao.getUsers().map { it?.asDomainModel() }

    suspend fun refreshUsers() {
        try {
            val users = usersApi.getUsers()
            appDatabase.usersDao.insertUsers(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}