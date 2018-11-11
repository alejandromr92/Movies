package alejandromr92.com.movies.utils;

import android.util.Log;

public abstract class LoggerUtils {

    public static void logMessage(String tag, String message){
        Log.d(tag, message);
    }

    public static void logError(String tag, String message, Exception exception){
        Log.e(tag, message, exception);
    }
}
