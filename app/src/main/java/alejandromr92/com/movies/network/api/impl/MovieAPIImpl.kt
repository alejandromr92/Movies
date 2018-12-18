package alejandromr92.com.movies.network.api.impl

import alejandromr92.com.movies.network.api.MovieAPI
import alejandromr92.com.movies.network.interceptor.NonSecurityInterceptor
import alejandromr92.com.movies.network.model.converter.MovieNetworkConverter
import alejandromr92.com.movies.network.service.Endpoints
import alejandromr92.com.movies.network.service.MovieService
import alejandromr92.com.movies.network.util.RetrofitClient
import alejandromr92.com.movies.storage.model.MovieDataStore
import io.reactivex.Single

class MovieAPIImpl: MovieAPI{
    private val service: MovieService = RetrofitClient.getMovieService(Endpoints.BASE_URL, NonSecurityInterceptor())

    override fun getPopularMovies(page: Int): Single<List<MovieDataStore>> =
        service.getPopularMovies(Endpoints.API_KEY, page).map { MovieNetworkConverter.convertListToStoreModel(it.results) }
}
