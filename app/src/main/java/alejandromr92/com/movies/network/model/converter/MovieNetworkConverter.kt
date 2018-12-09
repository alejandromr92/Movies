package alejandromr92.com.movies.network.model.converter

import alejandromr92.com.movies.network.model.response.MovieResponse
import alejandromr92.com.movies.network.service.Endpoints
import alejandromr92.com.movies.storage.model.MovieDataStore
import java.util.ArrayList

abstract class MovieNetworkConverter {
    companion object {
        fun convertListToStoreModel(networkList: List<MovieResponse>?): List<MovieDataStore> {
            val storeList = ArrayList<MovieDataStore>()

            if (networkList != null && networkList.isNotEmpty()) {
                for (m in networkList) {
                    storeList.add(convertToStoreModel(m))
                }
            }

            return storeList
        }

        private fun convertToStoreModel(network: MovieResponse): MovieDataStore {
            val store = MovieDataStore()

            store.title = network.title
            store.overview = network.overview
            store.pictureUrl = Endpoints.MOVIES_POSTERS_BASE_URL + network.poster_path
            store.year = network.release_date.substring(0, 4)

            return store
        }
    }
}