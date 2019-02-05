package alejandromr92.com.movies.di

import alejandromr92.com.movies.network.service.MovieService
import dagger.Component

@Component (modules = [NetworkModule::class])
interface NetworkComponent {

    fun getMovieService(): MovieService
}