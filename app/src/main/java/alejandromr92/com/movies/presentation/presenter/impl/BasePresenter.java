package alejandromr92.com.movies.presentation.presenter.impl;

import alejandromr92.com.movies.domain.interactor.base.Executor;
import alejandromr92.com.movies.domain.interactor.base.MainThread;

public abstract class BasePresenter {
    protected Executor executor;
    protected MainThread mainThread;

    public BasePresenter(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }
}
