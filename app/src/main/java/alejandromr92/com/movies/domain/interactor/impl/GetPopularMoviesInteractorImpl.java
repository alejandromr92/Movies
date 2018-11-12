package alejandromr92.com.movies.domain.interactor.impl;

import alejandromr92.com.movies.domain.interactor.GetPopularMoviesInteractor;
import alejandromr92.com.movies.domain.interactor.base.BaseInteractor;
import alejandromr92.com.movies.domain.interactor.base.Executor;
import alejandromr92.com.movies.domain.interactor.base.MainThread;
import alejandromr92.com.movies.domain.model.Movie;
import alejandromr92.com.movies.storage.repositories.MovieRepository;

import java.util.List;

public class GetPopularMoviesInteractorImpl extends BaseInteractor implements GetPopularMoviesInteractor, MovieRepository.PopularMoviesCallback {

    private MovieRepository movieRepository;
    private Callback callback;

    private int page;

    public GetPopularMoviesInteractorImpl(Executor threadExecutor, MainThread mainThread, MovieRepository movieRepository, Callback callback) {
        super(threadExecutor, mainThread);
        this.movieRepository = movieRepository;
        this.callback = callback;
    }

    @Override
    public void execute(int page) {
        this.page = page;
        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        movieRepository.getPopularMovies(page, this);
    }

    @Override
    public void onPopularMoviesRetrieved(List<Movie> movieList) {
        callback.onPopularMoviesRetrieved(movieList);
    }

    @Override
    public void onPopularMoviesRetrievingError(int errorCode) {
        callback.onPopularMoviesRetrievingError(errorCode);
    }
}
