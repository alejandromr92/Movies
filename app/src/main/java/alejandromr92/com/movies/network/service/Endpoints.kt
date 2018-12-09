package alejandromr92.com.movies.network.service

interface Endpoints {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val API_KEY = "93aea0c77bc168d8bbce3918cefefa45"
        const val POPULAR_MOVIES = "popular"
        const val MOVIES_POSTERS_BASE_URL = "http://image.tmdb.org/t/p/w154"
    }
}
