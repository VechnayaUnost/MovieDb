package by.zdzitavetskaya_darya.moviedb.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;

@Database(entities = {Movie.class}, version = AppDatabase.VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

   public abstract MovieDao getMovieDao();

    static final int VERSION = 1;
}
