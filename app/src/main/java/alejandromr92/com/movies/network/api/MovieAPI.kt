package alejandromr92.com.movies.network.api

import alejandromr92.com.movies.storage.model.MovieDataStore
import io.reactivex.Single

interface MovieAPI {
    fun getPopularMovies(page: Int) : Single<List<MovieDataStore>>
}