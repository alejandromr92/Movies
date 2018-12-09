package alejandromr92.com.movies.domain.interactor

import alejandromr92.com.movies.domain.model.Movie
import io.reactivex.Single

interface GetPopularMoviesInteractor {
    fun execute(page: Int, onComplete: (List<Movie>) -> Unit, onError: (Throwable) -> Unit)
}