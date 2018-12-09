package alejandromr92.com.movies.presentation.ui.activities

import alejandromr92.com.movies.R
import alejandromr92.com.movies.presentation.Constants
import alejandromr92.com.movies.presentation.model.MovieView
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter
import alejandromr92.com.movies.presentation.presenter.impl.GetPopularMoviesPresenterImpl
import alejandromr92.com.movies.presentation.ui.adapters.MovieListAdapter
import alejandromr92.com.movies.storage.repositories.impl.MovieRepositoryImpl
import alejandromr92.com.movies.utils.LoggerUtils
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.*

class MoviesActivity : BaseActivity(), GetPopularMoviesPresenter.View {

    private var getPopularMoviesPresenter: GetPopularMoviesPresenter? = null

    private var movieListAdapter: MovieListAdapter? = null

    private var moviesList: MutableList<MovieView>? = null

    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_movies
        this.page = Constants.DEFAULT_PAGE

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        movies_container_layout.requestFocus()
    }

    override fun initializePresenters() {
        super.initializePresenters()

        this.getPopularMoviesPresenter = GetPopularMoviesPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this,
            MovieRepositoryImpl()
        )
    }

    override fun configViews() {
        super.configViews()

        this.configRecyclerView()

        this.configSearchView()
    }

    private fun configRecyclerView() {
        this.moviesList = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        movie_list.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(movie_list.context, layoutManager.orientation)
        movie_list.addItemDecoration(dividerItemDecoration)

        this.movieListAdapter = MovieListAdapter(moviesList as ArrayList<MovieView>)
        movie_list.adapter = movieListAdapter

        movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // End of list reached and not filtering
                if (hasReachedBottom() && movies_searchview.query.toString().isEmpty()) {
                    getPopularMoviesPresenter!!.getPopularMovies(page)
                }
            }
        })

        movie_list.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(false)
            false
        }
    }

    private fun configSearchView() {
        movies_searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                movieListAdapter!!.filter.filter(newText)
                return false
            }
        })
    }

    private fun hasReachedBottom(): Boolean {
        val linearLayoutManager = movie_list.layoutManager as LinearLayoutManager
        return linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieListAdapter!!.itemCount - 1
    }

    override fun loadData() {
        super.loadData()
        this.getPopularMoviesPresenter!!.getPopularMovies(this.page)
    }

    override fun resetData() {
        super.resetData()

        this.page = Constants.DEFAULT_PAGE
        movies_searchview.setQuery("", false)
        this.moviesList!!.clear()
    }

    override fun onPopularMoviesRetrieved(retrievedMovies: List<MovieView>) {
        this.page++

        for (view in retrievedMovies) {
            if (!this.moviesList!!.contains(view)) {
                this.moviesList!!.add(view)
            }
        }

        this.movieListAdapter!!.notifyDataSetChanged()
    }

    override fun onPopularMoviesRetrievingError() {
        LoggerUtils.logError("MoviesActivity", "errorCode", Exception())
    }
}
