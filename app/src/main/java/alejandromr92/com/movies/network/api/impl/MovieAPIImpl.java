package alejandromr92.com.movies.network.api.impl;

import alejandromr92.com.movies.network.api.MovieAPI;
import alejandromr92.com.movies.network.interceptor.NonSecurityInterceptor;
import alejandromr92.com.movies.network.model.converter.MovieNetworkConverter;
import alejandromr92.com.movies.network.model.response.PopularMoviesResponse;
import alejandromr92.com.movies.network.service.Endpoints;
import alejandromr92.com.movies.network.service.MovieService;
import alejandromr92.com.movies.network.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Response;

public class MovieAPIImpl implements MovieAPI {

    private final static String TAG = MovieAPIImpl.class.getSimpleName();

    private MovieService service;

    public MovieAPIImpl() {
        this.service = RetrofitClient.getMovieService(Endpoints.BASE_URL, new NonSecurityInterceptor());
    }

    @Override
    public void getPopularMovies(PopularMoviesCallback callback) {

        Call<PopularMoviesResponse> call = service.getPopularMovies(Endpoints.API_KEY);

        try {
            Response<PopularMoviesResponse> response = call.execute();

            if (response.body().getResults() != 0) {
                callback.onPopularMoviesRetrieved(MovieNetworkConverter.convertListToStoreModel(response.body().getPopularMoviesList()));

            } else {
                throw new Exception("");
            }

        } catch (Exception e) {
            //TODO pass code to constant
            callback.onPopularMoviesRetrievingError(404);
        }
    }
}
