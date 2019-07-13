package by.zdzitavetskaya_darya.moviedb.presentation;

import io.reactivex.disposables.CompositeDisposable;
import moxy.MvpPresenter;
import moxy.MvpView;

public class BasePresenter <V extends MvpView> extends MvpPresenter<V> {

    protected CompositeDisposable compositeDisposable;

    public void onViewAttached() {
        compositeDisposable = new CompositeDisposable();
    }

    public void onViewDetached() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
