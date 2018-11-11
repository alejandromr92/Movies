package alejandromr92.com.movies.network.api;

import alejandromr92.com.movies.storage.model.MovieDataStore;

import java.util.List;

public interface MovieAPI {

    void getPopularMovies(PopularMoviesCallback callback);

    interface PopularMoviesCallback {
        void onPopularMoviesRetrieved(List<MovieDataStore> movieDataStoreList);
        void onPopularMoviesRetrievingError(int errorCode);
    }
}
