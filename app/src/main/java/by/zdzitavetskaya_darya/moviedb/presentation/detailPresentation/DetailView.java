package by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import moxy.MvpView;

public interface DetailView extends MvpView {

    void onMovieSuccess(Movie movie);
}
