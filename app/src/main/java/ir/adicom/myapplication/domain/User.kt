package ir.adicom.myapplication.domain

import ir.adicom.myapplication.database.BookmarkEntity
import ir.adicom.myapplication.database.UserEntity
import ir.adicom.myapplication.network.model.UserApiModel

data class User(
    val id: Int,
    val avatar: String,
    val username: String
)

fun User.asDatabaseModel(): BookmarkEntity {
    return BookmarkEntity(
        id = 0,
        avatar = avatar,
        username = username
    )

}