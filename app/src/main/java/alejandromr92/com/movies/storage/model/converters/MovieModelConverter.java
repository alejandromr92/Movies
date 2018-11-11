package alejandromr92.com.movies.storage.model.converters;

import alejandromr92.com.movies.domain.model.Movie;
import alejandromr92.com.movies.storage.model.MovieDataStore;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieModelConverter {

    public static List<MovieDataStore> convertListToStoreModel(List<Movie> domainList){
        List<MovieDataStore> storeList = new ArrayList<>();

        if (domainList != null && domainList.size() > 0){
            for (Movie m: domainList){
                storeList.add(convertToStoreModel(m));
            }
        }

        return storeList;
    }

    public static MovieDataStore convertToStoreModel(Movie domain){
        MovieDataStore store = new MovieDataStore();

        store.setTitle(domain.getTitle());
        store.setOverview(domain.getOverview());
        store.setPictureUrl(domain.getPictureUrl());
        store.setYear(domain.getYear());

        return store;
    }

    public static List<Movie> convertListToDomainModel(List<MovieDataStore> storeList){
        List<Movie> domainList = new ArrayList<>();

        if (storeList != null && storeList.size() > 0){
            for (MovieDataStore m: storeList){
                domainList.add(convertToDomainModel(m));
            }
        }

        return domainList;
    }

    public static Movie convertToDomainModel(MovieDataStore store){
        Movie domain = new Movie();

        domain.setTitle(store.getTitle());
        domain.setOverview(store.getOverview());
        domain.setPictureUrl(store.getPictureUrl());
        domain.setYear(store.getYear());

        return domain;
    }
}
