package alejandromr92.com.movies.network.model.request;

import com.google.gson.annotations.SerializedName;

public class PopularMoviesRequest {

    @SerializedName("api_key")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
