package alejandromr92.com.movies.domain.threading;

import alejandromr92.com.movies.domain.interactor.base.MainThread;
import android.os.Handler;
import android.os.Looper;

public class MainThreadImpl implements MainThread {

    private static MainThread mainThread;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }

    public static MainThread getInstance(){
        if(mainThread == null){
            mainThread = new MainThreadImpl();
        }

        return mainThread;
    }
}
