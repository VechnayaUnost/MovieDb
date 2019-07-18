package by.zdzitavetskaya_darya.moviedb.presentation.favouritePresentation;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import moxy.MvpView;

public interface FavouriteView extends MvpView {

    void onMoviesSuccess(List<Movie> movies);
}
