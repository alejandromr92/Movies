package alejandromr92.com.movies.presentation.presenter.impl

import alejandromr92.com.movies.domain.interactor.impl.GetPopularMoviesInteractorImpl
import alejandromr92.com.movies.domain.model.Movie
import alejandromr92.com.movies.presentation.model.converters.MovieViewconverter
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter
import alejandromr92.com.movies.storage.repositories.MovieRepository
import io.reactivex.Scheduler

class GetPopularMoviesPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: GetPopularMoviesPresenter.View,
    private val movieRepository: MovieRepository
) : GetPopularMoviesPresenter{

    override fun getPopularMovies(page: Int) {
        view.showProgress()
        val interactor = GetPopularMoviesInteractorImpl(movieRepository, mainThread, threadExecutor)
        interactor.execute(page, ::onPopularMoviesRetrieved, ::onPopularMoviesRetrievingError)
    }

    private fun onPopularMoviesRetrieved(movieList: List<Movie>){
        view.hideProgress()
        view.onPopularMoviesRetrieved(MovieViewconverter.convertListToViewModel(movieList))
    }

    private fun onPopularMoviesRetrievingError(throwable: Throwable){
        view.hideProgress()
        view.onPopularMoviesRetrievingError()
    }

}