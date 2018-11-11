package alejandromr92.com.movies.presentation.model.converters;

import alejandromr92.com.movies.domain.model.Movie;
import alejandromr92.com.movies.presentation.model.MovieView;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieViewconverter {

    public static List<MovieView> convertListToViewModel(List<Movie> domainList){
        List<MovieView> viewList = new ArrayList<>();

        if (domainList != null && domainList.size() > 0){
            for (Movie m: domainList){
                viewList.add(convertToViewModel(m));
            }
        }

        return viewList;
    }

    public static MovieView convertToViewModel(Movie domain){
        MovieView view = new MovieView();

        view.setTitle(domain.getTitle());
        view.setOverview(domain.getOverview());
        view.setPictureUrl(domain.getPictureUrl());
        view.setYear(domain.getYear());

        return view;
    }

    public static List<Movie> convertListToDomainModel(List<MovieView> viewList){
        List<Movie> domainList = new ArrayList<>();

        if (viewList != null && viewList.size() > 0){
            for (MovieView m: viewList){
                domainList.add(convertToDomainModel(m));
            }
        }

        return domainList;
    }

    public static Movie convertToDomainModel(MovieView view){
        Movie domain = new Movie();

        domain.setTitle(view.getTitle());
        domain.setOverview(view.getOverview());
        domain.setPictureUrl(view.getPictureUrl());
        domain.setYear(view.getYear());

        return domain;
    }
}
