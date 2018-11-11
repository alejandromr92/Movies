package alejandromr92.com.movies.network.util;

import alejandromr92.com.movies.network.Constants;
import alejandromr92.com.movies.network.service.MoviesService;
import android.support.annotation.Nullable;
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



}
