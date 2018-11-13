package alejandromr92.com.movies.network.model.converter;

import alejandromr92.com.movies.network.model.response.MovieResponse;
import alejandromr92.com.movies.network.service.Endpoints;
import alejandromr92.com.movies.storage.model.MovieDataStore;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieNetworkConverter {
    public static List<MovieDataStore> convertListToStoreModel(List<MovieResponse> networkList){
        List<MovieDataStore> storeList = new ArrayList<>();

        if (networkList != null && networkList.size() > 0){
            for (MovieResponse m: networkList){
                storeList.add(convertToStoreModel(m));
            }
        }

        return storeList;
    }

    public static MovieDataStore convertToStoreModel(MovieResponse network){
        MovieDataStore store = new MovieDataStore();

        store.setTitle(network.getTitle());
        store.setOverview(network.getOverview());
        store.setPictureUrl(Endpoints.MOVIES_POSTERS_BASE_URL + network.getPictureUrl());
        store.setYear(network.getReleaseDate().substring(0, 4));

        return store;
    }
}
