package alejandromr92.com.movies.network.interceptor;

import okhttp3.Interceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class NonSecurityInterceptor implements Interceptor {

    /**
     * Intercepts the chain and calls onOnIntercept defined method
     * @param chain to be intercepted
     * @return the result specified in onOnIntercept method
     * @throws IOException thrown
     */
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        return onOnIntercept(chain);
    }

    /**
     * Definition of an interceptor in order to handle Time Out Exceptions
     */
    private okhttp3.Response onOnIntercept(Interceptor.Chain chain) throws IOException {
        try {
            okhttp3.Response response = chain.proceed(chain.request());
            return response;
        }
        catch (SocketTimeoutException exception) {
            return null;
        }
    }
}
