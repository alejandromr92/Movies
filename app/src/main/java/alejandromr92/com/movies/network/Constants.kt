package alejandromr92.com.movies.network

interface Constants {
    companion object {
        val READ_TIME_OUT = 60L
        val WRITE_TIME_OUT = 60L
        val CONNECT_TIME_OUT = 60L

        val NETWORK_ERROR = 404

        val API_KEY = "api_key"
        val API_PAGE = "page"
    }
}
