package by.zdzitavetskaya_darya.moviedb.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM SubMovie WHERE isTopRated = :isTopRated")
    Single<List<SubMovie>> getTopRatedMovies(boolean isTopRated);

    @Query("SELECT * FROM SubMovie WHERE isUpcoming = :isUpcoming")
    Single<List<SubMovie>> getUpcomingMovies(boolean isUpcoming);

    @Query("SELECT * FROM Movie")
    Single<List<Movie>> getFavouriteMovies();

    @Query("SELECT * FROM Movie WHERE id = :id")
    Single<Movie> getMovieByIdFromFavourite(int id);

    @Query("SELECT * FROM SubMovie WHERE id = :id")
    Single<SubMovie> getMovieById(int id);

    @Insert
    Completable insert(Movie movie);

    @Delete
    Completable delete(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<SubMovie> subMovies);
}
