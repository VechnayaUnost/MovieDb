package by.zdzitavetskaya_darya.moviedb.presentation;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    void onMoviesSuccess(List<SubMovie> subMovies);

    @StateStrategyType(SkipStrategy.class)
    void onMessageShow();
}
