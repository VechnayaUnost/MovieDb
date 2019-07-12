package by.zdzitavetskaya_darya.moviedb.di.module;

import android.util.Log;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.api.MovieApi;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message ->
            Log.e("OK_HTTP",message));

    private static final OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build();

    @Provides
    @Singleton
    MovieApi getMovieApi() {
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(MovieApi.class);
    }
}
