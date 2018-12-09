package alejandromr92.com.movies.presentation.presenter

import alejandromr92.com.movies.presentation.model.MovieView

interface GetPopularMoviesPresenter {
    fun getPopularMovies(page: Int)

    interface View : BaseView {
        fun onPopularMoviesRetrieved(movieList: List<MovieView>)
        fun onPopularMoviesRetrievingError()
    }
}