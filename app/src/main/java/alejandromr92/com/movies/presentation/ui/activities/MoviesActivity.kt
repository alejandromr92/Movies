package alejandromr92.com.movies.presentation.ui.activities

import alejandromr92.com.movies.R
import alejandromr92.com.movies.presentation.Constants
import alejandromr92.com.movies.presentation.model.MovieView
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter
import alejandromr92.com.movies.presentation.presenter.impl.GetPopularMoviesPresenterImpl
import alejandromr92.com.movies.presentation.ui.adapters.MovieListAdapter
import alejandromr92.com.movies.storage.repositories.impl.MovieRepositoryImpl
import alejandromr92.com.movies.utils.LoggerUtils
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import butterknife.BindView
import butterknife.OnTouch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.lang.ref.WeakReference
import java.util.ArrayList

class MoviesActivity : BaseActivity(), GetPopularMoviesPresenter.View {

    private val TAG = MoviesActivity::class.java.simpleName

    @BindView(R.id.movies_container_layout)
    internal var containerLayout: ConstraintLayout? = null

    @BindView(R.id.movies_searchview)
    internal var moviesSearchView: SearchView? = null

    @BindView(R.id.movie_list)
    internal var moviesRecyclerView: RecyclerView? = null

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

        containerLayout!!.requestFocus()
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
        this.moviesRecyclerView!!.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(moviesRecyclerView!!.context, layoutManager.orientation)
        moviesRecyclerView!!.addItemDecoration(dividerItemDecoration)

        this.movieListAdapter = MovieListAdapter(moviesList, WeakReference(this))
        this.moviesRecyclerView!!.adapter = movieListAdapter

        this.moviesRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // End of list reached and not filtering
                if (hasReachedBottom() && moviesSearchView!!.query.toString().isEmpty()) {
                    getPopularMoviesPresenter!!.getPopularMovies(page)
                }
            }
        })
    }

    private fun configSearchView() {
        this.moviesSearchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        val linearLayoutManager = this.moviesRecyclerView!!.layoutManager as LinearLayoutManager
        return linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieListAdapter!!.itemCount - 1
    }

    override fun loadData() {
        super.loadData()
        this.getPopularMoviesPresenter!!.getPopularMovies(this.page)
    }

    override fun resetData() {
        super.resetData()

        this.page = Constants.DEFAULT_PAGE
        this.moviesSearchView!!.setQuery("", false)
        this.moviesList!!.clear()
    }

    @OnTouch(R.id.movie_list)
    fun onMoviesRecyclerviewTouch(v: View): Boolean {
        v.parent.requestDisallowInterceptTouchEvent(false)
        return false
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
        LoggerUtils.logError(TAG, 404.toString(), Exception())

    }
}
