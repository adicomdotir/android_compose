package ir.adicom.myapplication.network

import ir.adicom.myapplication.network.model.DetailsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("/users/{user}")
    suspend fun getDetails(@Path("user") user: String): DetailsApiModel
}