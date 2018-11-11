package alejandromr92.com.movies.domain.threading;

import alejandromr92.com.movies.domain.interactor.base.BaseInteractor;
import alejandromr92.com.movies.domain.interactor.base.Executor;
import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor implements Executor {
    private static volatile ThreadExecutor threadExecutor;
    private static final TimeUnit TIME_UNIT;
    private static final BlockingQueue<Runnable> WORK_QUEUE;
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor() {
        long keepAlive = 120L;
        this.mThreadPoolExecutor = new ThreadPoolExecutor(3, 5, keepAlive, TIME_UNIT, WORK_QUEUE);
    }

    public void execute(final BaseInteractor interactor) {
        this.mThreadPoolExecutor.submit(new Runnable() {
            public void run() {
                interactor.run();
                interactor.onFinished();
            }
        });
    }

    public void execute(@NonNull Runnable runnable) {
        this.mThreadPoolExecutor.execute(runnable);
    }

    public static Executor getInstance() {
        if (threadExecutor == null) {
            threadExecutor = new ThreadExecutor();
        }

        return threadExecutor;
    }

    static {
        TIME_UNIT = TimeUnit.SECONDS;
        WORK_QUEUE = new LinkedBlockingQueue();
    }
}
