package alejandromr92.com.movies.presentation.ui.activities;

import alejandromr92.com.movies.R;
import alejandromr92.com.movies.domain.threading.MainThreadImpl;
import alejandromr92.com.movies.domain.threading.ThreadExecutor;
import alejandromr92.com.movies.presentation.Constants;
import alejandromr92.com.movies.presentation.model.MovieView;
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter;
import alejandromr92.com.movies.presentation.presenter.impl.GetPopularMoviesPresenterImpl;
import alejandromr92.com.movies.presentation.ui.adapters.MovieListAdapter;
import alejandromr92.com.movies.storage.repositories.impl.MovieRepositoryImpl;
import alejandromr92.com.movies.utils.LoggerUtils;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import butterknife.BindView;
import butterknife.OnTouch;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends BaseActivity implements GetPopularMoviesPresenter.View{

    @BindView(R.id.movies_searchview)
    SearchView moviesSearchView;

    @BindView(R.id.movie_list)
    RecyclerView moviesRecyclerView;

    private GetPopularMoviesPresenter getPopularMoviesPresenter;

    private MovieListAdapter movieListAdapter;

    private List<MovieView> moviesList;

    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.layout = R.layout.activity_movies;
        this.page = Constants.DEFAULT_PAGE;

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializePresenters() {
        super.initializePresenters();

        this.getPopularMoviesPresenter = new GetPopularMoviesPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                new MovieRepositoryImpl(),
                this);
    }

    @Override
    protected void configViews() {
        super.configViews();

        this.configRecyclerView();

        this.configSearchView();
    }

    private void configRecyclerView(){
        this.moviesList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.moviesRecyclerView.setLayoutManager(layoutManager);

        this.movieListAdapter = new MovieListAdapter(moviesList, new WeakReference<Context>(this));
        this.moviesRecyclerView.setAdapter(movieListAdapter);

        this.moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // End of list reached and not filtering
                if (hasReachedBottom() && moviesSearchView.getQuery().toString().isEmpty()){
                    getPopularMoviesPresenter.getPopularMovies(page);
                }
            }
        });
    }

    private void configSearchView(){
        this.moviesSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private boolean hasReachedBottom(){
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.moviesRecyclerView.getLayoutManager();
        return linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieListAdapter.getItemCount() - 1;
    }

    @Override
    protected void loadData() {
        super.loadData();
        this.getPopularMoviesPresenter.getPopularMovies(this.page);
    }

    @Override
    protected void resetData() {
        super.resetData();

        this.page = Constants.DEFAULT_PAGE;
        this.moviesSearchView.setQuery("", false);
    }

    @Override
    public void onPopularMoviesRetrieved(List<MovieView> moviesList) {
        this.page++;

        for (MovieView view: moviesList){
            if (!this.moviesList.contains(view)){
                this.moviesList.add(view);
            }
        }

        this.movieListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPopularMoviesRetrievingError(int errorCode) {
        LoggerUtils.logError(TAG, String.valueOf(errorCode), new Exception());
    }

    @OnTouch(R.id.movie_list)
    public boolean onMoviesRecyclerviewTouch(View v) {
        v.getParent().requestDisallowInterceptTouchEvent(false);
        return false;
    }

}
