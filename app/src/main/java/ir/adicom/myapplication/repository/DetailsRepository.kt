package ir.adicom.myapplication.repository

import ir.adicom.myapplication.database.AppDatabase
import ir.adicom.myapplication.database.asDomainModel
import ir.adicom.myapplication.domain.Details
import ir.adicom.myapplication.domain.User
import ir.adicom.myapplication.domain.asDatabaseModel
import ir.adicom.myapplication.network.DetailsApi
import ir.adicom.myapplication.network.model.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val detailsApi: DetailsApi,
    private val appDatabase: AppDatabase
) {

    fun getUserDetails(user: String): Flow<Details?> =
        appDatabase.usersDao.getDetails(user).map { it?.asDomainModel() }

    suspend fun refreshDetails(user: String) {
        try {
            val userDetails = detailsApi.getDetails(user)
            appDatabase.usersDao.insertDetails(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

    suspend fun addUser(username: String) {
        try {
            appDatabase.usersDao.insertBookmarkUser(
                User(
                    id = 0,
                    avatar = "",
                    username = username
                ).asDatabaseModel()
            )
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}