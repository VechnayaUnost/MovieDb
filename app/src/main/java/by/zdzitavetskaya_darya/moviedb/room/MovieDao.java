package by.zdzitavetskaya_darya.moviedb.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie WHERE isTopRated = :isTopRated")
    Single<List<Movie>> getTopRatedMovies(boolean isTopRated);

    @Query("SELECT * FROM movie WHERE isUpcoming = :isUpcoming")
    Single<List<Movie>> getUpcomingMovies(boolean isUpcoming);

    @Query("SELECT * FROM movie WHERE isFavourite = :isFavourite")
    Single<List<Movie>> getFavouriteMovies(boolean isFavourite);

    @Insert
    Completable insert(Movie movie);

    @Delete
    Completable delete(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Movie> movies);
}
