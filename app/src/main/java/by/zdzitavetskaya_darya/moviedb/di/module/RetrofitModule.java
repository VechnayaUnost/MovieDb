package by.zdzitavetskaya_darya.moviedb.di.module;

import android.util.Log;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.api.MovieApi;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message ->
            Log.e("OK_HTTP",message));

    private static final Interceptor interceptor = chain -> chain.proceed(chain
                    .request()
                    .newBuilder()
                    .url(chain
                            .request()
                            .url()
                            .newBuilder()
                            .addQueryParameter("api_key", Constants.API_KEY)
                            .build())
                    .build()
                );

    private static final OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build();

    @Provides
    @Singleton
    MovieApi getMovieApi() {
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(MovieApi.class);
    }
}
