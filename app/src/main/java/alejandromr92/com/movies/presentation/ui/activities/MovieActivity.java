package alejandromr92.com.movies.presentation.ui.activities;

import alejandromr92.com.movies.R;
import alejandromr92.com.movies.domain.threading.MainThreadImpl;
import alejandromr92.com.movies.domain.threading.ThreadExecutor;
import alejandromr92.com.movies.presentation.model.MovieView;
import alejandromr92.com.movies.presentation.presenter.GetPopularMoviesPresenter;
import alejandromr92.com.movies.presentation.presenter.impl.GetPopularMoviesPresenterImpl;
import alejandromr92.com.movies.storage.repositories.impl.MovieRepositoryImpl;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

public class MovieActivity extends BaseActivity implements GetPopularMoviesPresenter.View{

    private GetPopularMoviesPresenter getPopularMoviesPresenter;

    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.layout = R.layout.activity_main;
        this.page = 1;

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
    protected void loadData() {
        super.loadData();

        this.getPopularMoviesPresenter.getPopularMovies(this.page);
    }

    @Override
    public void onPopularMoviesRetrieved(List<MovieView> movieList) {
        this.page++;
    }

    @Override
    public void onPopularMoviesRetrievingError(int errorCode) {

    }
}
