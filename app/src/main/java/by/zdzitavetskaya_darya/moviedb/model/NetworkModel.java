package by.zdzitavetskaya_darya.moviedb.model;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.api.MovieApi;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import io.reactivex.Single;

public class NetworkModel {

    @Inject
    MovieApi movieApi;

    public NetworkModel() {
        App.getAppComponent().inject(this);
    }

    public Single<MovieCover> getTopRatedMovies(final int page) {
        return movieApi.getTopRated(page);
    }

    public Single<MovieCover> getUpcomingMovies(final int page) {
        return movieApi.getUpcoming(page);
    }

    public Single<MovieCover> getSearchedMovies(final int page, final String query) {
        return movieApi.searchMovies(page, query);
    }

    public Single<Movie> getMovieById(final int id) {
        return movieApi.getMovieById(id);
    }
}
