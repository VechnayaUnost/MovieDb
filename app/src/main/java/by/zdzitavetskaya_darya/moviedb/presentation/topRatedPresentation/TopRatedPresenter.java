package by.zdzitavetskaya_darya.moviedb.presentation.topRatedPresentation;

import java.util.List;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import by.zdzitavetskaya_darya.moviedb.presentation.BasePresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseView;
import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class TopRatedPresenter extends BasePresenter<BaseView> {

    @Inject
    DatabaseModel databaseModel;

    @Inject
    NetworkModel networkModel;

    public TopRatedPresenter() {
        App.getAppComponent().inject(this);
        getMoviesFromNetwork();
    }

    private void getMoviesFromNetwork() {
        compositeDisposable.add(
                networkModel.getTopRatedMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<MovieCover>() {
                            @Override
                            public void onSuccess(final MovieCover movieCover) {
                                for (final Movie movie: movieCover.getMovies()) {
                                    movie.setTopRated(true);
                                }
                                insertMoviesInDatabase(movieCover.getMovies());
                                getViewState().onMoviesSuccess(movieCover.getMovies());
                            }

                            @Override
                            public void onError(final Throwable e) {
                                getMoviesFromDatabase();
                                getViewState().onMessageShow();
                                e.printStackTrace();
                            }
                        }));
    }

    public void getMoviesFromDatabase() {
        compositeDisposable.add(
                databaseModel.getTopRatedMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Movie>>() {
                            @Override
                            public void onSuccess(final List<Movie> movies) {
                                getViewState().onMoviesSuccess(movies);
                            }

                            @Override
                            public void onError(final Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }

    private void insertMoviesInDatabase(final List<Movie> movies) {
        compositeDisposable.add(
                databaseModel.insertMovies(movies)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }
}
