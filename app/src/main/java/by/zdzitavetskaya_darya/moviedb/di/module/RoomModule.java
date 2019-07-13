package by.zdzitavetskaya_darya.moviedb.di.module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.room.AppDatabase;
import by.zdzitavetskaya_darya.moviedb.room.MovieDao;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final AppDatabase appDatabase;

    public RoomModule(final Application application) {
        appDatabase = Room
                .databaseBuilder(application, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Provides
    @Singleton
    MovieDao providesRecordDao(final AppDatabase appDatabase) {
        return appDatabase.getMovieDao();
    }
}
