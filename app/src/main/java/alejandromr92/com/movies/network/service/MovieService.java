package alejandromr92.com.movies.network.service;

import alejandromr92.com.movies.network.model.request.PopularMoviesRequest;
import alejandromr92.com.movies.network.model.response.PopularMoviesResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MovieService {

    @GET(Endpoints.POPULAR_MOVIES)
    Call<PopularMoviesResponse> getPopularMovies(@Body PopularMoviesRequest request);
}
