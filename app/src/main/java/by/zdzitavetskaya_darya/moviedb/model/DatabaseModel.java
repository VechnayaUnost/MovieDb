package by.zdzitavetskaya_darya.moviedb.model;

import java.util.List;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.room.MovieDao;
import io.reactivex.Completable;
import io.reactivex.Single;

public class DatabaseModel {

    @Inject
    MovieDao movieDao;

    public DatabaseModel() {
        App.getAppComponent().inject(this);
    }

    public Single<List<Movie>> getTopRatedMovies() {
        return movieDao.getTopRatedMovies(true);
    }

    public Single<List<Movie>> getUpcomingMovies() {
        return movieDao.getUpcomingMovies(true);
    }

    public Single<List<Movie>> getFavouriteMovies() {
        return movieDao.getFavouriteMovies(true);
    }

    public Completable insertMovies(List<Movie> movies) {
        return movieDao.insertAll(movies);
    }

    public Completable insertMovie(Movie movie) {
        return movieDao.insert(movie);
    }

    public Completable deleteMovie(Movie movie) {
        return movieDao.delete(movie);
    }
}
