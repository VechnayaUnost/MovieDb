package by.zdzitavetskaya_darya.moviedb.presentation.searchPresentation;

import androidx.appcompat.widget.SearchView;

import com.jakewharton.rxbinding3.appcompat.RxSearchView;

import javax.inject.Inject;

import by.zdzitavetskaya_darya.moviedb.App;
import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import by.zdzitavetskaya_darya.moviedb.presentation.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchMovieView> {

    @Inject
    NetworkModel networkModel;

    public SearchPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getMoviesFromNetwork(final int page, final String query) {
        compositeDisposable.add(
                networkModel.getSearchedMovies(page, query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<MovieCover>() {
                            @Override
                            public void onSuccess(final MovieCover movieCover) {
                                if (movieCover.getSubMovies().size() != 0) {
                                    getViewState().onMoviesSuccess(movieCover.getSubMovies());
                                } else {
                                    getViewState().onMessageShow();
                                }
                            }

                            @Override
                            public void onError(final Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }

    public void searchObservable(final SearchView searchView) {
        RxSearchView.queryTextChanges(searchView)
                .map(CharSequence::toString)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(final String s) {
                        getViewState().onMessageHide();
                        if (!s.isEmpty()) {
                            getMoviesFromNetwork(1, s);
                        } else {
                            getViewState().onListClear();
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
