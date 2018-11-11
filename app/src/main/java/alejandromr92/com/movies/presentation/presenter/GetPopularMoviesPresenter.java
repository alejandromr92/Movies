package alejandromr92.com.movies.presentation.presenter;

import alejandromr92.com.movies.presentation.model.MovieView;

import java.util.List;

public interface GetPopularMoviesPresenter {

    void getPopularMovies();

    interface View {
        void onPopularMoviesRetrieved(List<MovieView> movieList);
        void onPopularMoviesRetrievingError(int errorCode);
    }
}
