package by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class DetailPresenter extends BasePresenter<DetailView> {

    @Inject
    DatabaseModel databaseModel;

    @Inject
    NetworkModel networkModel;

    public DetailPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getMovieFromNetwork(final int id) {
        compositeDisposable.add(
                networkModel.getMovieById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Movie>() {
                            @Override
                            public void onSuccess(final Movie movie) {
                                getViewState().onMovieSuccess(movie);
                            }

                            @Override
                            public void onError(final Throwable e) {
                                getMovieFromDatabase(id);
                                e.printStackTrace();
                            }
                        }));
    }

    public void getMovieFromDatabase(final int id) {
        compositeDisposable.add(
                databaseModel.getMovieById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Movie>() {
                            @Override
                            public void onSuccess(final Movie movie) {
                                getViewState().onMovieSuccess(movie);
                            }

                            @Override
                            public void onError(final Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }
}
