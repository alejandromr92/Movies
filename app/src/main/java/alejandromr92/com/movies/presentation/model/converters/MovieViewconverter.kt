package alejandromr92.com.movies.presentation.model.converters

import alejandromr92.com.movies.domain.model.Movie
import alejandromr92.com.movies.presentation.model.MovieView

import java.util.ArrayList

abstract class MovieViewconverter {

    companion object {

        fun convertListToViewModel(domainList: List<Movie>?): List<MovieView> {
            val viewList = ArrayList<MovieView>()

            if (domainList != null && domainList.isNotEmpty()) {
                for (m in domainList) {
                    viewList.add(convertToViewModel(m))
                }
            }

            return viewList
        }

        private fun convertToViewModel(domain: Movie): MovieView {
            val view = MovieView()

            view.title = domain.title
            view.overview = domain.overview
            view.pictureUrl = domain.pictureUrl
            view.year = domain.year

            return view
        }

        fun convertListToDomainModel(viewList: List<MovieView>?): List<Movie> {
            val domainList = ArrayList<Movie>()

            if (viewList != null && viewList.isNotEmpty()) {
                for (m in viewList) {
                    domainList.add(convertToDomainModel(m))
                }
            }

            return domainList
        }

        private fun convertToDomainModel(view: MovieView): Movie {
            val domain = Movie()

            domain.title = view.title
            domain.overview = view.overview
            domain.pictureUrl = view.pictureUrl
            domain.year = view.year

            return domain
        }
    }
}
