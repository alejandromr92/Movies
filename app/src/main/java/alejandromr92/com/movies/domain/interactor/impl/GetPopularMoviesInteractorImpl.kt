package alejandromr92.com.movies.domain.interactor.impl

import alejandromr92.com.movies.domain.interactor.GetPopularMoviesInteractor
import alejandromr92.com.movies.domain.model.Movie
import alejandromr92.com.movies.storage.repositories.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetPopularMoviesInteractorImpl(
    private val movieRepository: MovieRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) : GetPopularMoviesInteractor {

    private var subscription: Disposable = Disposables.empty()

    override fun execute(page: Int, onComplete: (List<Movie>) -> Unit, onError: (Throwable) -> Unit){
        subscription = movieRepository.getPopularMovies(page)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }
}
