package alejandromr92.com.movies.network.util;

import alejandromr92.com.movies.network.Constants;
import alejandromr92.com.movies.network.service.MovieService;
import android.support.annotation.Nullable;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    /**
     * Get Retrofit Instance
     */

    private static Retrofit.Builder getRetrofitInstance(@Nullable Interceptor interceptor) {
        try {
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
                            .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .addInterceptor(interceptor)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create());
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Get Movie API.
     *
     * @return Movie aervice
     */
    public static MovieService getMovieService(String url, @Nullable Interceptor interceptor) {
        return getRetrofitInstance(interceptor).baseUrl(url).build().create(MovieService.class);
    }

}
