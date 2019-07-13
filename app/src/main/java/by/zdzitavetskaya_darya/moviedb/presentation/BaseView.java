package by.zdzitavetskaya_darya.moviedb.presentation;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import moxy.MvpView;

public interface BaseView extends MvpView {

    void onMoviesSuccess(List<Movie> movies);

    void onMessageShow();
}
