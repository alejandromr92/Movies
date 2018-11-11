package alejandromr92.com.movies.storage.repositories.impl;

import alejandromr92.com.movies.network.api.MovieAPI;
import alejandromr92.com.movies.network.api.impl.MovieAPIImpl;
import alejandromr92.com.movies.storage.model.MovieDataStore;
import alejandromr92.com.movies.storage.model.converters.MovieModelConverter;
import alejandromr92.com.movies.storage.repositories.MovieRepository;

import java.util.List;

public class MovieRepositoryImpl implements MovieRepository, MovieAPI.PopularMoviesCallback {

    private MovieAPI movieAPI;

    private PopularMoviesCallback popularMoviesCallback;

    public MovieRepositoryImpl() {
        this.movieAPI = new MovieAPIImpl();
    }

    @Override
    public void getPopularMovies(PopularMoviesCallback callback) {
        this.popularMoviesCallback = callback;
        this.movieAPI.getPopularMovies(this);
    }

    @Override
    public void onPopularMoviesRetrieved(List<MovieDataStore> movieDataStoreList) {
        this.popularMoviesCallback.onPopularMoviesRetrieved(MovieModelConverter.convertListToDomainModel(movieDataStoreList));
    }

    @Override
    public void onPopularMoviesRetrievingError(int errorCode) {
        this.popularMoviesCallback.onPopularMoviesRetrievingError(errorCode);
    }
}
