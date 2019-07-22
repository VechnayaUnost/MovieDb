package by.zdzitavetskaya_darya.moviedb.presentation.upcomingPresentation;

import java.util.List;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import by.zdzitavetskaya_darya.moviedb.presentation.BasePresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class UpcomingPresenter extends BasePresenter<BaseView> {

    @Inject
    DatabaseModel databaseModel;

    @Inject
    NetworkModel networkModel;

    public UpcomingPresenter() {
        App.getAppComponent().inject(this);
        getMoviesFromNetwork(1);
    }

    public void getMoviesFromNetwork(final int page) {
        compositeDisposable.add(
                networkModel.getUpcomingMovies(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<MovieCover>() {
                            @Override
                            public void onSuccess(final MovieCover movieCover) {
                                for (final SubMovie subMovie : movieCover.getSubMovies()) {
                                    subMovie.setUpcoming(true);
                                }
                                insertMoviesInDatabase(movieCover.getSubMovies());
                                getViewState().onMoviesSuccess(movieCover.getSubMovies());
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
                databaseModel.getUpcomingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<SubMovie>>() {
                            @Override
                            public void onSuccess(final List<SubMovie> subMovies) {
                                getViewState().onMoviesSuccess(subMovies);
                            }

                            @Override
                            public void onError(final Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }

    private void insertMoviesInDatabase(final List<SubMovie> subMovies) {
        compositeDisposable.add(
                databaseModel.insertMovies(subMovies)
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
