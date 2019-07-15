package by.zdzitavetskaya_darya.moviedb.model;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.api.MovieApi;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import io.reactivex.Single;

public class NetworkModel {

    @Inject
    MovieApi movieApi;

    public NetworkModel() {
        App.getAppComponent().inject(this);
    }

    public Single<MovieCover> getTopRatedMovies() {
        return movieApi.getTopRated();
    }

    public Single<MovieCover> getUpcomingMovies() {
        return movieApi.getUpcoming();
    }

    public Single<MovieCover> getSearchedMovies(final String query) {
        return movieApi.searchMovies(query);
    }
}
