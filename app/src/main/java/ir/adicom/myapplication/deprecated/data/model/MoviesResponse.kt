package ir.adicom.myapplication.deprecated.data.model

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieItem>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
