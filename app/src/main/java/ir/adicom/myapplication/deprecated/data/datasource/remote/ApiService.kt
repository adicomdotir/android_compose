import ir.adicom.myapplication.deprecated.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/JsonSandbox/apis/movies.json")
    suspend fun getMovies(): Response<MoviesResponse>
}
