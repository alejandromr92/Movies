package alejandromr92.com.movies.storage.repositories.impl

import alejandromr92.com.movies.domain.model.Movie
import alejandromr92.com.movies.network.api.MovieAPI
import alejandromr92.com.movies.network.api.impl.MovieAPIImpl
import alejandromr92.com.movies.storage.model.converters.MovieModelConverter
import alejandromr92.com.movies.storage.repositories.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl: MovieRepository{
    private val movieAPI: MovieAPI = MovieAPIImpl()

    override fun getPopularMovies(page: Int): Single<List<Movie>> =
        movieAPI.getPopularMovies(page).map { MovieModelConverter.convertListToDomainModel(it) }
}
