package alejandromr92.com.movies.presentation.presenter.impl;

import alejandromr92.com.movies.domain.interactor.GetPopularMoviesInteractor;
import alejandromr92.com.movies.domain.interactor.base.Executor;
import alejandromr92.com.movies.domain.interactor.base.MainThread;
import alejandromr92.com.movies.domain.interactor.impl.GetPopularMoviesInteractorImpl;
import alejandromr92.com.movies.domain.model.Movie;
import alejandromr92.com.movies.presentation.model.converters.MovieViewconverter;
import alejandromr92.com.movies.presentation.presenter.BasePresenter;
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter;
import alejandromr92.com.movies.storage.repositories.MovieRepository;

import java.util.List;

public class GetPopularMoviesPresenterImpl extends BasePresenter implements GetPopularMoviesPresenter, GetPopularMoviesInteractor.Callback {

    private View view;

    private MovieRepository movieRepository;

    public GetPopularMoviesPresenterImpl(Executor executor, MainThread mainThread, MovieRepository movieRepository, View view) {
        super(executor, mainThread);
        this.view = view;
        this.movieRepository = movieRepository;
    }

    @Override
    public void getPopularMovies(int page) {
        GetPopularMoviesInteractor interactor = new GetPopularMoviesInteractorImpl(executor, mainThread, movieRepository, this);
        interactor.execute(page);
    }

    @Override
    public void onPopularMoviesRetrieved(List<Movie> movieList) {
        view.onPopularMoviesRetrieved(MovieViewconverter.convertListToViewModel(movieList));
    }

    @Override
    public void onPopularMoviesRetrievingError(int errorCode) {
        view.onPopularMoviesRetrievingError(errorCode);
    }
}
