package by.zdzitavetskaya_darya.moviedb.presentation.favouritePresentation;

import java.util.List;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class FavouritePresenter extends BasePresenter<FavouriteView> {

    @Inject
    DatabaseModel databaseModel;

    public FavouritePresenter() {
        App.getAppComponent().inject(this);
    }

    public void getMoviesFromDatabase() {
        compositeDisposable.add(
                databaseModel.getFavouriteMovies()
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
}
