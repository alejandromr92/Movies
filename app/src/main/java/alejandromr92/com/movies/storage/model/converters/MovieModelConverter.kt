package alejandromr92.com.movies.storage.model.converters

import alejandromr92.com.movies.domain.model.Movie
import alejandromr92.com.movies.storage.model.MovieDataStore

import java.util.ArrayList

object MovieModelConverter {

    fun convertListToStoreModel(domainList: List<Movie>?): List<MovieDataStore> {
        val storeList = ArrayList<MovieDataStore>()

        if (domainList != null && domainList.size > 0) {
            for (m in domainList) {
                storeList.add(convertToStoreModel(m))
            }
        }

        return storeList
    }

    fun convertToStoreModel(domain: Movie): MovieDataStore {
        val store = MovieDataStore()

        store.title = domain.title
        store.overview = domain.overview
        store.pictureUrl = domain.pictureUrl
        store.year = domain.year

        return store
    }

    fun convertListToDomainModel(storeList: List<MovieDataStore>?): List<Movie> {
        val domainList = ArrayList<Movie>()

        if (storeList != null && storeList.size > 0) {
            for (m in storeList) {
                domainList.add(convertToDomainModel(m))
            }
        }

        return domainList
    }

    fun convertToDomainModel(store: MovieDataStore): Movie {
        val domain = Movie()

        domain.title = store.title
        domain.overview = store.overview
        domain.pictureUrl = store.pictureUrl
        domain.year = store.year

        return domain
    }
}
