package alejandromr92.com.movies.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("page")
    private long page;

    @SerializedName("total_results")
    private long results;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("results")
    @Expose
    private List<MovieResponse> popularMoviesList;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieResponse> getPopularMoviesList() {
        return popularMoviesList;
    }

    public void setPopularMoviesList(List<MovieResponse> popularMoviesList) {
        this.popularMoviesList = popularMoviesList;
    }
}
