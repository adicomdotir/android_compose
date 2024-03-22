package ir.adicom.myapplication.data.model

import com.squareup.moshi.Json

data class MovieItem(
    @Json(name ="adult")
    val adult: Boolean,
    @Json(name ="backdrop_path")
    val backdropPath: String,
    @Json(name ="genre_ids")
    val genreIds: List<Int>,
    @Json(name ="id")
    val id: Int,
    @Json(name ="original_language")
    val originalLanguage: String,
    @Json(name ="original_title")
    val originalTitle: String,
    @Json(name ="overview")
    val overview: String,
    @Json(name ="popularity")
    val popularity: Double,
    @Json(name ="poster_path")
    val posterPath: String,
    @Json(name ="release_date")
    val releaseDate: String,
    @Json(name ="title")
    val title: String,
    @Json(name ="video")
    val video: Boolean,
    @Json(name ="vote_average")
    val voteAverage: Double,
    @Json(name ="vote_count")
    val voteCount: Int
) {
    companion object {
        fun mock() = MovieItem(
            adult = false,
            backdropPath = "/5a4JdoFwll5DRtKMe7JLuGQ9yJm.jpg",
            genreIds = listOf(
                18,
                878,
                28
            ),
            id = 695721,
            originalLanguage = "en",
            originalTitle = "The Hunger Games: The Ballad of Songbirds & Snakes",
            overview = "64 years before he becomes the tyrannical president of Panem, Coriolanus Snow sees a chance for a change in fortunes when he mentors Lucy Gray Baird, the female tribute from District 12.",
            popularity = 2786.228,
            posterPath = "https://image.tmdb.org/t/p/w342/mBaXZ95R2OxueZhvQbcEWy2DqyO.jpg",
            releaseDate = "2023-11-15",
            title = "The Hunger Games: The Ballad of Songbirds & Snakes",
            video = false,
            voteAverage = 7.218,
            voteCount = 965
        )
    }
}
