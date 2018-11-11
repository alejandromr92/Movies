package alejandromr92.com.movies.network.service;

import alejandromr92.com.movies.network.Constants;
import alejandromr92.com.movies.network.model.response.PopularMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET(Endpoints.POPULAR_MOVIES)
    Call<PopularMoviesResponse> getPopularMovies(@Query(Constants.API_KEY) String apiKey);
}
