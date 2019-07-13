package by.zdzitavetskaya_darya.moviedb.presentation;

import io.reactivex.disposables.CompositeDisposable;
import moxy.MvpPresenter;
import moxy.MvpView;

public abstract class BasePresenter <V extends MvpView> extends MvpPresenter<V> {

    final protected CompositeDisposable compositeDisposable;

    BasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}
