package alejandromr92.com.movies.domain.interactor;

import alejandromr92.com.movies.domain.model.Movie;

import java.util.List;

public interface GetPopularMoviesInteractor {

    void execute(int page);

    interface Callback {
        void onPopularMoviesRetrieved(List<Movie> movieList);
        void onPopularMoviesRetrievingError(int errorCode);
    }
}
