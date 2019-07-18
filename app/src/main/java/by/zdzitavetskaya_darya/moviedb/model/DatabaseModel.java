package by.zdzitavetskaya_darya.moviedb.model;

import java.util.List;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import by.zdzitavetskaya_darya.moviedb.room.MovieDao;
import io.reactivex.Completable;
import io.reactivex.Single;

public class DatabaseModel {

    @Inject
    MovieDao movieDao;

    public DatabaseModel() {
        App.getAppComponent().inject(this);
    }

    public Single<List<SubMovie>> getTopRatedMovies() {
        return movieDao.getTopRatedMovies(true);
    }

    public Single<List<SubMovie>> getUpcomingMovies() {
        return movieDao.getUpcomingMovies(true);
    }

    public Single<List<Movie>> getFavouriteMovies() {
        return movieDao.getFavouriteMovies();
    }

    public Single<Movie> getMovieByIdFromFavourite(final int id) {
        return movieDao.getMovieByIdFromFavourite(id);
    }

    public Single<SubMovie> getMovieById(final int id) {
        return movieDao.getMovieById(id);
    }

    public Completable insertMovies(final List<SubMovie> subMovies) {
        return movieDao.insertAll(subMovies);
    }

    public Completable insertMovie(final Movie movie) {
        return movieDao.insert(movie);
    }

    public Completable deleteMovie(final Movie movie) {
        return movieDao.delete(movie);
    }
}
