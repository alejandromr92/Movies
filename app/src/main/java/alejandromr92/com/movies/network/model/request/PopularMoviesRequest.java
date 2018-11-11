package alejandromr92.com.movies.network.model.request;

import alejandromr92.com.movies.network.service.Endpoints;
import com.google.gson.annotations.SerializedName;

public class PopularMoviesRequest {

    @SerializedName("api_key")
    private String apiKey;

    public PopularMoviesRequest() {
        this.apiKey = Endpoints.API_KEY;
    }
}
