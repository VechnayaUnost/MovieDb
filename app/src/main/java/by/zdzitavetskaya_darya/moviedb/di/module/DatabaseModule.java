package by.zdzitavetskaya_darya.moviedb.di.module;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public DatabaseModel databaseModel() {
        return new DatabaseModel();
    }

}
