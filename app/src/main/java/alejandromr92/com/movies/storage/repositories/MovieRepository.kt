package alejandromr92.com.movies.storage.repositories

import alejandromr92.com.movies.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<List<Movie>>
}