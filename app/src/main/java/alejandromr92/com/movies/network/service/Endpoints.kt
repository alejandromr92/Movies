package alejandromr92.com.movies.network.service

interface Endpoints {
    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/movie/"
        val API_KEY = "93aea0c77bc168d8bbce3918cefefa45"
        val POPULAR_MOVIES = "popular"
        val MOVIES_POSTERS_BASE_URL = "http://image.tmdb.org/t/p/w154"
    }
}
