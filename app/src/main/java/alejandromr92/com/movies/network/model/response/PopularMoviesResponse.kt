package alejandromr92.com.movies.network.model.response

data class PopularMoviesResponse (
    val page: Long,
    val total_results: Long,
    val total_pages: Long,
    val results: List<MovieResponse>
)