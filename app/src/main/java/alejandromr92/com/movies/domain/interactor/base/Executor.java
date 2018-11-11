package alejandromr92.com.movies.domain.interactor.base;

public interface Executor extends java.util.concurrent.Executor {
    void execute(BaseInteractor baseInteractor);

    void execute(Runnable runnable);
}
