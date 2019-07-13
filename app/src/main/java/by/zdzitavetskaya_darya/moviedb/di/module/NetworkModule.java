package by.zdzitavetskaya_darya.moviedb.di.module;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkModel networkModel() {
        return new NetworkModel();
    }
}
