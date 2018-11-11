package alejandromr92.com.movies.domain.interactor.base;

public abstract class BaseInteractor implements Interactor{

    protected Executor threadExecutor;
    protected MainThread mainThread;

    protected volatile boolean isCanceled;
    protected volatile boolean isRunning;

    public BaseInteractor(Executor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void cancel(){
        this.isCanceled = true;
        this.isRunning = false;
    }

    public void onFinished(){
        this.isRunning = false;
        this.isCanceled = false;
    }

    public void execute(){
        this.isRunning = true;
        this.threadExecutor.execute(this);
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
