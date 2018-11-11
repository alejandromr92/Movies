package alejandromr92.com.movies.storage.repositories;

import alejandromr92.com.movies.domain.model.Movie;

import java.util.List;

public interface MovieRepository {

    void getPopularMovies(PopularMoviesCallback callback);

    interface PopularMoviesCallback {
        void onPopularMoviesRetrieved(List<Movie> movieList);
        void onPopularMoviesRetrievingError(int errorCode);
    }
}
