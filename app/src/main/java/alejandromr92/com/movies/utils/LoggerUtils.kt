package alejandromr92.com.movies.utils

import android.util.Log

/**
 * Helper class to register logs.
 */
object LoggerUtils {

    fun logMessage(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun logError(tag: String, message: String, exception: Exception) {
        Log.e(tag, message, exception)
    }
}
