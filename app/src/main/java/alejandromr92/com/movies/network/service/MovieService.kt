package alejandromr92.com.movies.network.service

import alejandromr92.com.movies.network.Constants
import alejandromr92.com.movies.network.model.response.PopularMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET(Endpoints.POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(Constants.API_KEY) apiKey: String,
        @Query(Constants.API_PAGE) page: Int
    ): Single<PopularMoviesResponse>
}
